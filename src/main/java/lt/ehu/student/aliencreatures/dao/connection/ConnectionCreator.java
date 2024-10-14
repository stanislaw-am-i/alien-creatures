package lt.ehu.student.aliencreatures.dao.connection;

import lt.ehu.student.aliencreatures.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionCreator.class);
    public static final String PROPERTIES = "properties/database.properties";
    private static ConnectionCreator instance = new ConnectionCreator();

    // todo: Create a Pool Connection;
    private ConnectionCreator() {}

    public static ConnectionCreator getInstance() {
        return instance;
    }

    public Connection createConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());

            Properties prop = new Properties();
            prop.load(ConnectionCreator.class.getClassLoader().getResourceAsStream(PROPERTIES));

            String url = (String) prop.get("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException | IOException e) {
            LOGGER.error("Failed to create connection with DB.", e);
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Failed to close connection with DB.", e);
                throw new RuntimeException(e);
            }
        }
    }

}
