package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandConstant;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.service.AlienService;
import lt.ehu.student.aliencreatures.service.impl.AlienServiceImpl;
import lt.ehu.student.aliencreatures.validator.Validator;
import lt.ehu.student.aliencreatures.validator.impl.ValidatorImpl;

import java.util.List;

public class AddAlienCommand implements Command {
    private final Validator validator = ValidatorImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            Router router = new Router();
            String name = request.getParameter(CommandConstant.NAME_PARAM);
            String lor = request.getParameter(CommandConstant.LOR_PARAM);
            AlienService alienService = new AlienServiceImpl();
            String page = CommandConstant.ALIENS_PAGE;
            if (validator.validateNotEmpty(name) || validator.validateNotEmpty(lor)) {
                request.setAttribute(CommandConstant.ATTR_ERROR_MESSAGE, CommandConstant.ERROR_INVALID_PARAMS);
                router.setPage(page);
                router.setRedirect();
                return router;
            }

            boolean isExists = alienService.checkDuplicate(name, lor);
            if (isExists) {
                request.setAttribute(CommandConstant.ATTR_ERROR_MESSAGE, CommandConstant.ERROR_DUPLICATE_ALIEN);
            } else if (alienService.addNewCharacter(name, lor)) {
                List<Alien> aliens = alienService.fetchListOfCharacters();
                request.setAttribute(CommandConstant.ATTR_ALIENS_LIST, aliens);
            } else {
                request.setAttribute(CommandConstant.ATTR_ERROR_MESSAGE, CommandConstant.ERROR_ALIEN_SAVE_FAILED);
            }

            router.setPage(page);
            router.setRedirect();
            return router;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

}
