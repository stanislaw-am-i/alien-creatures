package lt.ehu.student.aliencreatures.dao.impl;

import lt.ehu.student.aliencreatures.dao.BaseDao;
import lt.ehu.student.aliencreatures.dao.UserDao;
import lt.ehu.student.aliencreatures.dao.connection.ConnectionCreator;
import lt.ehu.student.aliencreatures.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String AUTH_QUERY = "SELECT password FROM users WHERE username = ?";
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {}

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public String fetchPassword(String login) {
        try {
            String passFromDb = "";
            Connection connection = ConnectionCreator.getInstance().createConnection();
            PreparedStatement statement = connection.prepareStatement(AUTH_QUERY);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passFromDb = resultSet.getString(1);
            }
            ConnectionCreator.getInstance().closeConnection(connection);
            return passFromDb;
        } catch (SQLException e) {
            LOGGER.error("Failed to select a record from DB.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User update(User user) {
        return null;
    }
}
