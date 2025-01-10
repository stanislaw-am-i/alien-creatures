package lt.ehu.student.aliencreatures.pool;

import lt.ehu.student.aliencreatures.util.PropertyLoaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_POOL_SIZE = 8;
    private static final ReentrantLock INSTANCE_LOCK = new ReentrantLock();
    public static final String PROPERTIES = "properties/database.properties";
    private static final String DB_URL_PROPERTY_NAME = "db.url";
    private static final String DB_USER_PROPERTY_NAME = "db.user";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";

    private BlockingQueue<ProxyConnection> free = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
    private BlockingQueue<ProxyConnection> used = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
    private final ReentrantLock lock;

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private ConnectionPool() throws SQLException, IOException {
        Properties prop = PropertyLoaderUtil.loadProperties(PROPERTIES);

        String url = (String) prop.get(DB_URL_PROPERTY_NAME);
        String user = prop.getProperty(DB_USER_PROPERTY_NAME);
        String password = prop.getProperty(DB_PASSWORD_PROPERTY_NAME);

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            Connection connection = DriverManager.getConnection(url, user, password);
            free.add(new ProxyConnection(connection));
        }

        lock = new ReentrantLock();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            try {
                INSTANCE_LOCK.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } catch (IOException | SQLException e) {
                LOGGER.error(e);
                throw new ExceptionInInitializerError(e);
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        lock.lock();
        ProxyConnection connection = null;
        try {
            connection = free.take();
            used.offer(connection);
            return connection;
        } catch (InterruptedException e) {
            LOGGER.error(e);
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }

        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        if (!(connection instanceof ProxyConnection)) {
            LOGGER.warn("Connection is not a ProxyConnection.");
            return false;
        }

        lock.lock();
        try {
            if (used.remove((ProxyConnection) connection)) {
                boolean isOffered = free.offer((ProxyConnection) connection);

                if (!isOffered) {
                    LOGGER.warn("Failed to release connection: free connection pool is full.");
                }
                return isOffered;
            } else {
                LOGGER.warn("Failed to release connection: the connection was not found in the used pool.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void destroyPool() {
        lock.lock();
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                free.take().reallyClose();
            }
            deregisterDrivers();
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new RuntimeException(e);
            }
        });
    }
}
