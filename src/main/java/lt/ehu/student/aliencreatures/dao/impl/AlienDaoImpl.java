package lt.ehu.student.aliencreatures.dao.impl;

import lt.ehu.student.aliencreatures.controller.Controller;
import lt.ehu.student.aliencreatures.dao.BaseDao;
import lt.ehu.student.aliencreatures.dao.AlienDao;
import lt.ehu.student.aliencreatures.dao.connection.ConnectionCreator;
import lt.ehu.student.aliencreatures.entity.Alien;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienDaoImpl extends BaseDao<Alien> implements AlienDao {
    private static final Logger LOGGER = LogManager.getLogger(AlienDaoImpl.class);
    private static final String ADD_CHARACTER_QUERY = "INSERT INTO characters (name, lor) VALUES (?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT id, name, lor FROM characters";
    private static final String CHECK_DUPLICATE_QUERY = "SELECT COUNT(*) FROM characters WHERE name = ? AND lor = ?";
    private static AlienDaoImpl instance = new AlienDaoImpl();

    private AlienDaoImpl() {}

    public static AlienDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(Alien alien) {
        boolean result;
        try {
            Connection connection = ConnectionCreator.getInstance().createConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_CHARACTER_QUERY);
            statement.setString(1, alien.getName());
            statement.setString(2, alien.getLor());
            int rowsAffected = statement.executeUpdate();
            ConnectionCreator.getInstance().closeConnection(connection);
            result = rowsAffected == 1;
        } catch (SQLException e) {
            LOGGER.error("Failed to insert alien.", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public boolean delete(Alien alien) {
        return false;
    }

    @Override
    public List<Alien> findAll() {
        List<Alien> aliens = new ArrayList<>();
        try {
            Connection connection = ConnectionCreator.getInstance().createConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(FIND_ALL_QUERY);

            while (result.next()) {
                Alien alien = new Alien();
                alien.setId(result.getInt("id"));
                alien.setName(result.getString("name"));
                alien.setLor(result.getString("lor"));

                aliens.add(alien);
            }

            ConnectionCreator.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to fetch the list of aliens records.", e);
            throw new RuntimeException(e);
        }

        return aliens;
    }

    @Override
    public Alien update(Alien alien) {
        return null;
    }

    @Override
    public boolean checkDuplicate(Alien alien) {
        try {
            Connection connection = ConnectionCreator.getInstance().createConnection();
            PreparedStatement statement = connection.prepareStatement(CHECK_DUPLICATE_QUERY);
            statement.setString(1, alien.getName());
            statement.setString(2, alien.getLor());
            ResultSet result = statement.executeQuery();

            ConnectionCreator.getInstance().closeConnection(connection);
            return result.next() && result.getInt(1) > 0;
        } catch (SQLException e) {
            LOGGER.error("Failed to select a record from DB.", e);
            throw new RuntimeException(e);
        }
    }
}
