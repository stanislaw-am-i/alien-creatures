package lt.ehu.student.aliencreatures.dao.impl;

import lt.ehu.student.aliencreatures.dao.UserDao;
import lt.ehu.student.aliencreatures.dao.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {
    @Override
    public String authenticate(String login, String passEnc) {

        Connection connection = ConnectionCreator.createConnection();
        //Statement statement = connection.createStatement();


        return null;
    }
}
