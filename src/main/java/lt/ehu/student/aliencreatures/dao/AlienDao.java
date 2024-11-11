package lt.ehu.student.aliencreatures.dao;

import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.exception.DaoException;

public interface AlienDao {
    boolean checkDuplicate(Alien alien) throws DaoException;
}
