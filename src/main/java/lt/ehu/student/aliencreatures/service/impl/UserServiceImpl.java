package lt.ehu.student.aliencreatures.service.impl;

import lt.ehu.student.aliencreatures.dao.UserDao;
import lt.ehu.student.aliencreatures.dao.impl.UserDaoImpl;
import lt.ehu.student.aliencreatures.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean authenticate(String login, String password) {
        // todo  take encrypted password; encoding
        String passEncoded = password;
        UserDao userDao = new UserDaoImpl();
        String passResult = userDao.authenticate(login, passEncoded);
        return login.equals(password);
    }
}
