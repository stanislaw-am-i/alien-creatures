package lt.ehu.student.aliencreatures.service.impl;

import lt.ehu.student.aliencreatures.dao.UserDao;
import lt.ehu.student.aliencreatures.dao.impl.UserDaoImpl;
import lt.ehu.student.aliencreatures.entity.User;
import lt.ehu.student.aliencreatures.exception.DaoException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.mail.EmailContent;
import lt.ehu.student.aliencreatures.mail.EmailContentFactory;
import lt.ehu.student.aliencreatures.mail.EmailSender;
import lt.ehu.student.aliencreatures.mail.EmailType;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.service.ServiceConstant;
import lt.ehu.student.aliencreatures.util.EncryptionUtil;
import lt.ehu.student.aliencreatures.util.PropertyUtil;
import lt.ehu.student.aliencreatures.validator.Validator;
import lt.ehu.student.aliencreatures.validator.impl.ValidatorImpl;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final Validator validator = ValidatorImpl.getInstance();

    public static final String MAIL_PROPERTIES = "properties/mail.properties";

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
    public boolean signUp(String username, String email, String password) throws ServiceException {
        if (!validator.validateUsername(username)) {
            throw new ServiceException(ServiceConstant.INVALID_USERNAME_EXP);
        }
        if (!validator.validateEmail(email)) {
            throw new ServiceException(ServiceConstant.INVALID_EMAIL_FORMAT_EXP);
        }
        if (!validator.validatePassword(password)) {
            throw new ServiceException(ServiceConstant.INVALID_PASSWORD_EXP);
        }

        Optional<User> optionalUser;

        optionalUser = findByUsername(username);
        if (optionalUser.isPresent()) {
            throw new ServiceException(ServiceConstant.USER_ALREADY_EXISTS_EXP);
        }

        optionalUser = findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new ServiceException(ServiceConstant.USER_ALREADY_EXISTS_EXP);
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setConfirmationCode(EncryptionUtil.doHashingWithSalt(email));
        user.setPassword(EncryptionUtil.doHashingWithSalt(password));

        return saveNewUser(user);
    }

    @Override
    public boolean sendEmailToVerifyUser(String username, String email, String instance) throws ServiceException {
        try {
            Properties prop = PropertyUtil.loadProperties(MAIL_PROPERTIES);
            String code = EncryptionUtil.doHashingWithSalt(email);
            String url = instance + ServiceConstant.CONFIRM_REGISTRATION_URL + code;

            EmailContent confirmationEmail = EmailContentFactory.createEmailContent(EmailType.REGISTRATION_CONFIRMATION, username, url);
            EmailSender emailSender = new EmailSender(email, confirmationEmail, prop);
            emailSender.send();
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public Optional<User> findByUsername(String username) throws ServiceException {
        try {
            return userDao.findByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException(ServiceConstant.FIND_USER_BY_USERNAME_FAILED_EXP, e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        try {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(ServiceConstant.FIND_USER_BY_EMAIL_FAILED_EXP, e);
        }
    }

    @Override
    public Optional<User> findByConformationCode(String code) throws ServiceException {
        try {
            return userDao.findByConformationCode(code);
        } catch (DaoException e) {
            // todo: change exp msg
            throw new ServiceException(ServiceConstant.FIND_USER_BY_EMAIL_FAILED_EXP, e);
        }
    }

    @Override
    public boolean activateRegistration(String code) throws ServiceException {
        Optional<User> optionalUser = findByConformationCode(code);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(true);
            updateUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean saveNewUser(User user) throws ServiceException {
        try {
            return UserDaoImpl.getInstance().insert(user);
        } catch (DaoException e) {
            throw new ServiceException(ServiceConstant.SAVE_USER_FAILED_EXP, e);
        }
    }

    @Override
    public User updateUser(User user) throws ServiceException {
        try {
            return UserDaoImpl.getInstance().update(user);
        } catch (DaoException e) {
            // todo: change exp msg
            throw new ServiceException(ServiceConstant.SAVE_USER_FAILED_EXP, e);
        }
    }

}
