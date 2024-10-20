package lt.ehu.student.aliencreatures.service;

import lt.ehu.student.aliencreatures.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean signUp(String username, String email, String password);
}
