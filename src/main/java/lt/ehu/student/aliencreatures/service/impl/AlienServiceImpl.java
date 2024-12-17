package lt.ehu.student.aliencreatures.service.impl;

import lt.ehu.student.aliencreatures.dao.impl.AlienDaoImpl;
import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.exception.DaoException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.page.PaginatedResult;
import lt.ehu.student.aliencreatures.service.AlienService;

import java.util.List;

public class AlienServiceImpl implements AlienService {
    private static final AlienServiceImpl instance = new AlienServiceImpl();

    private AlienServiceImpl() {}

    public static AlienServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addNewCharacter(String name, String lor) throws ServiceException {
        try {
            return AlienDaoImpl.getInstance().insert(new Alien(name, lor));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addNewCharacter(Alien alien) throws ServiceException {
        try {
            return AlienDaoImpl.getInstance().insert(alien);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkDuplicate(String name, String lor) throws ServiceException {
        try {
            return AlienDaoImpl.getInstance().checkDuplicate(new Alien(name, lor));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Alien> fetchListOfCharacters() throws ServiceException {
        try {
            return AlienDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public PaginatedResult<Alien> fetchAliensForPage(String pageParam, String pageSizeParam) throws ServiceException {
        try {
            int currentPage = 1;
            if (pageParam != null) {
                currentPage = Integer.parseInt(pageParam);
            }

            int pageSize = 5;
            if (pageSizeParam != null) {
                pageSize = Integer.parseInt(pageSizeParam);
            }

            int totalRecords = AlienDaoImpl.getInstance().countAliens();
            int offset = (currentPage - 1) * pageSize;

            List<Alien> aliens = AlienDaoImpl.getInstance().fetchAliens(pageSize, offset);
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            return new PaginatedResult<>(aliens, currentPage, totalPages, pageSize);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAlien(Alien alien) throws ServiceException {
        try {
            return AlienDaoImpl.getInstance().delete(alien);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
