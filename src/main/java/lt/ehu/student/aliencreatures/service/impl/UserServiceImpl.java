package lt.ehu.student.aliencreatures.service.impl;

import lt.ehu.student.aliencreatures.dao.UserDao;
import lt.ehu.student.aliencreatures.dao.impl.UserDaoImpl;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.util.EncryptionUtil;

public class UserServiceImpl implements UserService {
    @Override
    public boolean authenticate(String login, String password) {
        String passEncoded = EncryptionUtil.doHashingWithSalt(password);
        UserDao userDao = UserDaoImpl.getInstance();
        String passResult = userDao.fetchPassword(login);
        return passEncoded.equals(passResult);
    }
}
