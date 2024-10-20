package lt.ehu.student.aliencreatures.dao.impl;

import lt.ehu.student.aliencreatures.dao.BaseDao;
import lt.ehu.student.aliencreatures.dao.UserDao;
import lt.ehu.student.aliencreatures.entity.User;
import lt.ehu.student.aliencreatures.exception.DaoException;
import lt.ehu.student.aliencreatures.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String AUTH_QUERY = "SELECT password FROM users WHERE username = ?";
    private static final String FIND_BY_LOGIN_QUERY = "SELECT id, username, email FROM users WHERE username = ? LIMIT 1";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT id, username, email FROM users WHERE email = ? LIMIT 1";
    private static final String INSERT_USER_QUERY = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {}

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public String fetchPassword(String login) throws DaoException {
        try {
            String passFromDb = "";
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(AUTH_QUERY);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passFromDb = resultSet.getString(1);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            return passFromDb;
        } catch (SQLException e) {
            LOGGER.error("Failed to select a record from DB.", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findByUsername(String login) {
        try {
            Optional<User> userOptional = Optional.empty();
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_QUERY);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId((int) resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                userOptional = Optional.of(user);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            return userOptional;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            Optional<User> userOptional = Optional.empty();
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_QUERY);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId((int) resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                userOptional = Optional.of(user);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            return userOptional;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(User user) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            int rowsInserted = statement.executeUpdate();

            ConnectionPool.getInstance().releaseConnection(connection);

            return rowsInserted > 0;
        } catch (SQLException e) {
            LOGGER.error("Failed to insert user into the DB.", e);
            throw new RuntimeException(e);
        }
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
