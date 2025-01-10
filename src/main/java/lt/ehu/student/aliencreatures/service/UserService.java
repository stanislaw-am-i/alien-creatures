package lt.ehu.student.aliencreatures.service;

import lt.ehu.student.aliencreatures.entity.User;
import lt.ehu.student.aliencreatures.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean signUp(String username, String email, String password) throws ServiceException;
    boolean signUp(Map<String, String> params) throws ServiceException;
    boolean sendEmailToVerifyUser(String username, String email, String url) throws ServiceException;
    Optional<User> findByUsername(String username) throws ServiceException;
    Optional<User> findByEmail(String email) throws ServiceException;
    Optional<User> findByConformationCode(String code) throws ServiceException;
    boolean activateRegistration(String code) throws ServiceException;
    boolean saveNewUser(User user) throws ServiceException;
    Optional<User> updateUserProfile(Map<String, String> params) throws ServiceException;
    User updateUser(User user) throws ServiceException;
}
