package lt.ehu.student.aliencreatures.dao.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    public static final String PROPERTIES = "properties/database.properties";
    public static Connection createConnection() {
        // todo: Create a Pool Connection; Handle Exceptions.
        Connection connection;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());

            Properties prop = new Properties();
            prop.load(ConnectionCreator.class.getClassLoader().getResourceAsStream(PROPERTIES));

            String url = (String) prop.get("db.url");
            connection = DriverManager.getConnection(url, prop);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
