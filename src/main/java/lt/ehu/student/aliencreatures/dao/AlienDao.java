package lt.ehu.student.aliencreatures.dao;

import lt.ehu.student.aliencreatures.entity.Alien;

public interface AlienDao {
    boolean checkDuplicate(Alien alien);
}
