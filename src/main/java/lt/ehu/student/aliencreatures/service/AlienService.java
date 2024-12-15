package lt.ehu.student.aliencreatures.service;

import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.page.PaginatedResult;

import java.util.List;

public interface AlienService {
    boolean addNewCharacter(String name, String lor) throws ServiceException;
    boolean addNewCharacter(Alien alien) throws ServiceException;
    boolean checkDuplicate(String name, String lor) throws ServiceException;
    List<Alien> fetchListOfCharacters() throws ServiceException;
    PaginatedResult<Alien> fetchAliensForPage(int currentPage, int pageSize) throws ServiceException;
}
