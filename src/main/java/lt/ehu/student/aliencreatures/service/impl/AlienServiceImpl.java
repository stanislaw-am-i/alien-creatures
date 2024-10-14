package lt.ehu.student.aliencreatures.service.impl;

import lt.ehu.student.aliencreatures.dao.impl.AlienDaoImpl;
import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.service.AlienService;

import java.util.List;

public class AlienServiceImpl implements AlienService {

    @Override
    public boolean addNewCharacter(String name, String lor) {
        return AlienDaoImpl.getInstance().insert(new Alien(name, lor));
    }

    @Override
    public boolean checkDuplicate(String name, String lor) {
        return AlienDaoImpl.getInstance().checkDuplicate(new Alien(name, lor));
    }

    @Override
    public List<Alien> fetchListOfCharacters() {
        return AlienDaoImpl.getInstance().findAll();
    }

}
