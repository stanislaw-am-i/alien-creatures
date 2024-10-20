package lt.ehu.student.aliencreatures.service.impl;

import lt.ehu.student.aliencreatures.dao.UserDao;
import lt.ehu.student.aliencreatures.dao.impl.UserDaoImpl;
import lt.ehu.student.aliencreatures.entity.User;
import lt.ehu.student.aliencreatures.exception.DaoException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.util.EncryptionUtil;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();
    private final UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        try {
            String passEncoded = EncryptionUtil.doHashingWithSalt(password);
            String passResult = userDao.fetchPassword(login);
            return passEncoded.equals(passResult);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean signUp(String username, String email, String password) {
        // todo: validation of email, login and password

        Optional<User> optionalUser = userDao.findByUsername(username);
        if (optionalUser.isPresent()) {
            throw new RuntimeException("User Already Exists.");
        }

        optionalUser = userDao.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new RuntimeException("User Already Exists.");
        }

        String hashedPassword = EncryptionUtil.doHashingWithSalt(password);
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);

        return UserDaoImpl.getInstance().insert(user);
    }
}
