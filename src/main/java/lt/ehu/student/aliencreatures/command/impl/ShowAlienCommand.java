package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.controller.PagePath;
import lt.ehu.student.aliencreatures.controller.Parameter;
import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.page.PaginatedResult;
import lt.ehu.student.aliencreatures.service.AlienService;
import lt.ehu.student.aliencreatures.service.impl.AlienServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowAlienCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowAlienCommand.class);
    private final AlienService alienService = AlienServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String page = PagePath.MAIN_PAGE;
        Router router = new Router();
        try {
            String pageParam = request.getParameter(Parameter.ATTR_PAGE);
            String pageSizeParam = request.getParameter(Parameter.ATTR_PAGE_SIZE);

            PaginatedResult<Alien> paginatedResult = alienService.fetchAliensForPage(pageParam, pageSizeParam);

            request.setAttribute(Parameter.ATTR_CURRENT_PAGE_NUMBER, paginatedResult.getCurrentPage());
            request.setAttribute(Parameter.ATTR_PAGE_SIZE, paginatedResult.getPageSize());
            request.setAttribute(Parameter.ATTR_TOTAL_PAGES, paginatedResult.getTotalPages());
            request.setAttribute(Parameter.ATTR_ALIENS_LIST, paginatedResult.getItems());
            request.setAttribute(Parameter.COMMAND_PARAM, "SHOW_ALIEN");

            router.setPage(page);
            return router;
        } catch (ServiceException e) {
            LOGGER.debug(e.getMessage());
            throw new CommandException(e);
        }
    }
}
