package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandConstant;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.service.AlienService;
import lt.ehu.student.aliencreatures.service.impl.AlienServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowAlienCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowAlienCommand.class);
    private final AlienService alienService = AlienServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        boolean isLogin = session.getAttribute(CommandConstant.ATTR_USER_NAME) != null;
        String page = CommandConstant.MAIN_PAGE;
        Router router = new Router();
        try {
            List<Alien> aliens = alienService.fetchListOfCharacters();
            System.out.println(aliens.size());
            request.setAttribute(CommandConstant.ATTR_ALIENS_LIST, aliens);
            router.setPage(page);
            return router;
        } catch (ServiceException e) {
            LOGGER.debug(e.getMessage());
            throw new CommandException(e);
        }
    }
}
