package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.controller.PagePath;
import lt.ehu.student.aliencreatures.controller.Parameter;
import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.service.AlienService;
import lt.ehu.student.aliencreatures.service.impl.AlienServiceImpl;
import lt.ehu.student.aliencreatures.validator.Validator;
import lt.ehu.student.aliencreatures.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class AddAlienCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddAlienCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            Router router = new Router();
            String name = request.getParameter(Parameter.NAME_PARAM);
            String lor = request.getParameter(Parameter.LOR_PARAM);

            HttpSession session = request.getSession();
            Integer userId = (session != null && session.getAttribute(Parameter.ATTR_CURRENT_USER_ID) != null)
                    ? (Integer) session.getAttribute(Parameter.ATTR_CURRENT_USER_ID)
                    : null;

            Part imagePart = request.getPart("file");
            InputStream imageStream = imagePart.getInputStream();
            byte[] imageData = imageStream.readAllBytes();

            Alien alien = new Alien(name, lor);
            alien.setImage(imageData);

            if (userId != null) {
                alien.setUserId(userId);
            }

            AlienService alienService = AlienServiceImpl.getInstance();
            String page = PagePath.ADD_ALIEN_PAGE;
            if (name.isBlank() || lor.isBlank()) {
                request.setAttribute(Parameter.ATTR_ERROR_MESSAGE, Parameter.ERROR_INVALID_PARAMS);
                router.setPage(page);
                router.setRedirect();
                return router;
            }

            boolean isExists = alienService.checkDuplicate(name, lor);
            if (isExists) {
                request.setAttribute(Parameter.ATTR_ERROR_MESSAGE, Parameter.ERROR_DUPLICATE_ALIEN);
            } else {
                boolean isCreated = alienService.addNewCharacter(alien);
                request.setAttribute(Parameter.ATTR_SUCCESS_MESSAGE, isCreated);
            }

            router.setPage(page);
            return router;
        } catch (ServiceException | ServletException | IOException e) {
            LOGGER.debug(e);
            throw new CommandException(e);
        }
    }

}
