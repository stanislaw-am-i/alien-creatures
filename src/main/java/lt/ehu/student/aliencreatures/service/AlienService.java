package lt.ehu.student.aliencreatures.service;

import lt.ehu.student.aliencreatures.entity.Alien;

import java.util.List;

public interface AlienService {
    boolean addNewCharacter(String name, String lor);
    boolean checkDuplicate(String name, String lor);
    List<Alien> fetchListOfCharacters();
}
