package lt.ehu.student.aliencreatures.dao;

import lt.ehu.student.aliencreatures.entity.AbstractEntity;
import lt.ehu.student.aliencreatures.entity.User;
import lt.ehu.student.aliencreatures.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    String fetchPassword(String login) throws DaoException;
    Optional<User> findByUsername(String login) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    Optional<User> findByConformationCode(String code) throws DaoException;
    boolean findByLoginExcludingId(int userId, String userName, String email) throws DaoException;
}
