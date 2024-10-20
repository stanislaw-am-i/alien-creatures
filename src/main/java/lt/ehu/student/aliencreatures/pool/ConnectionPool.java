package lt.ehu.student.aliencreatures.pool;

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

    private BlockingQueue<ProxyConnection> free = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
    private BlockingQueue<ProxyConnection> used = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
    private final ReentrantLock lock;

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionPool() throws SQLException, IOException {
        DriverManager.registerDriver(new org.postgresql.Driver());

        Properties prop = new Properties();
        prop.load(ConnectionPool.class.getClassLoader().getResourceAsStream(PROPERTIES));

        String url = (String) prop.get("db.url");
        String user = prop.getProperty("db.user");
        String password = prop.getProperty("db.password");

        // todo: As Stream
        for (int i = 0; i < 8; i++) {
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                INSTANCE_LOCK.lock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        lock.lock();
        try {
            ProxyConnection connection = free.take();
            used.offer(connection);
            return connection;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public boolean releaseConnection(Connection connection) {
        if (!(connection instanceof ProxyConnection)) {
            LOGGER.warn("Connection is not a ProxyConnection."); // todo: debug or error?
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
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
                throw new RuntimeException(e);
            }
        });
    }
}
