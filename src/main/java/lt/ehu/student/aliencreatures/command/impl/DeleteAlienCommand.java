package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandConstant;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.page.PaginatedResult;
import lt.ehu.student.aliencreatures.service.AlienService;
import lt.ehu.student.aliencreatures.service.impl.AlienServiceImpl;
import lt.ehu.student.aliencreatures.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteAlienCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteAlienCommand.class);
    private final AlienService alienService = AlienServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            String page = CommandConstant.MAIN_PAGE;
            Router router = new Router();
            router.setPage(page);

            String alienId = request.getParameter("alienId");
            Alien alien = new Alien();
            alien.setId(Integer.valueOf(alienId));
            boolean isDeleted = alienService.deleteAlien(alien);
            request.setAttribute(CommandConstant.ATTR_SUCCESS_MESSAGE, isDeleted);

            String pageParam = request.getParameter("page");
            String pageSizeParam = request.getParameter("pageSize");

            PaginatedResult<Alien> paginatedResult = alienService.fetchAliensForPage(pageParam, pageSizeParam);
            request.setAttribute(CommandConstant.ATTR_ALIENS_LIST, paginatedResult.getItems());

            return router;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
