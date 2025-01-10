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
    private static final String FIND_BY_LOGIN_QUERY = "SELECT id, username, email, is_active, role FROM users WHERE username = ? LIMIT 1";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT id, username, email, is_active, role FROM users WHERE email = ? LIMIT 1";
    private static final String FIND_BY_CONFIRMATION_CODE_QUERY = "SELECT id, username, email, is_active, role FROM users WHERE confirmation_code = ? LIMIT 1";
    private static final String INSERT_USER_QUERY = "INSERT INTO users (username, email, password, confirmation_code) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE users set email = ?, username = ?, is_active = ? WHERE id = ?";
    private static final String FIND_BY_LOGIN_EXCLUDING_ID_QUERY = "SELECT id, username, email, is_active, role FROM users WHERE id != ? AND (username = ? OR email = ?) LIMIT 1";

    private static final String USER_ID_FIELD = "id";
    private static final String USER_EMAIL_FIELD = "email";
    private static final String USER_USERNAME_FIELD = "username";
    private static final String USER_IS_ACTIVE_FIELD = "is_active";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {}

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public String fetchPassword(String login) throws DaoException {
        Connection connection = null;
        try {
            String passFromDb = "";
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(AUTH_QUERY);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passFromDb = resultSet.getString(1);
            }

            return passFromDb;
        } catch (SQLException e) {
            LOGGER.error("Failed to select a record from DB.", e);
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) throws DaoException {
        return findByField(FIND_BY_LOGIN_QUERY, username);
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        return findByField(FIND_BY_EMAIL_QUERY, email);
    }

    @Override
    public Optional<User> findByConformationCode(String code) throws DaoException {
        return findByField(FIND_BY_CONFIRMATION_CODE_QUERY, code);
    }

    @Override
    public boolean findByLoginExcludingId(int userId, String userName, String email) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_EXCLUDING_ID_QUERY);
            statement.setLong(1, userId);
            statement.setString(2, userName);
            statement.setString(3, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            LOGGER.error("Failed to select a record from DB.", e);
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    private Optional<User> findByField(String query, String param) throws DaoException {
        Connection connection = null;
        try {
            Optional<User> userOptional = Optional.empty();
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, param);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId((int) resultSet.getLong(USER_ID_FIELD));
                user.setUsername(resultSet.getString(USER_USERNAME_FIELD));
                user.setEmail(resultSet.getString(USER_EMAIL_FIELD));

                String role = resultSet.getString("role");
                user.setRole(role.equalsIgnoreCase("ADMIN") ? User.Role.ADMIN : (role.equalsIgnoreCase("MODER") ? User.Role.MODER :  User.Role.USER));

                boolean isActive = resultSet.getBoolean(USER_IS_ACTIVE_FIELD);
                user.setStatus(isActive ? User.Status.ACTIVE : User.Status.INACTIVE);

                userOptional = Optional.of(user);
            }

            return userOptional;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public boolean insert(User user) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getConfirmationCode());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            LOGGER.error("Failed to insert user into the DB.", e);
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
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
    public User update(User user) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setBoolean(3, user.getStatus() == User.Status.ACTIVE);
            statement.setLong(4, user.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                LOGGER.debug("User with ID " + user.getId() + " was successfully updated.");
                return user;
            } else {
                throw new DaoException("No user was updated. User ID: " + user.getId());
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to update user.", e);
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
