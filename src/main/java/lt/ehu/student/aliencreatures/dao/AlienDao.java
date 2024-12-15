package lt.ehu.student.aliencreatures.dao;

import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.exception.DaoException;

import java.util.List;

public interface AlienDao {
    boolean checkDuplicate(Alien alien) throws DaoException;
    List<Alien> fetchAliens(int limit, int offset) throws DaoException;
    int countAliens() throws  DaoException;
}
