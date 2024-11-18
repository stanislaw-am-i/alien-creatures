package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandConstant;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.controller.Controller;
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
import java.util.List;

public class AddAlienCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddAlienCommand.class);
    private final Validator validator = ValidatorImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            Router router = new Router();
            String name = request.getParameter(CommandConstant.NAME_PARAM);
            String lor = request.getParameter(CommandConstant.LOR_PARAM);
            Part imagePart = request.getPart("file");
            // todo: make validation for image uploading
            InputStream imageStream = imagePart.getInputStream();
            byte[] imageData = imageStream.readAllBytes();

            Alien alien = new Alien(name, lor);
            alien.setImage(imageData);

            AlienService alienService = new AlienServiceImpl();
            String page = CommandConstant.ALIENS_PAGE;
            if (!validator.validateNotEmpty(name) || !validator.validateNotEmpty(lor)) {
                LOGGER.debug(45 + " Add Alien");
                request.setAttribute(CommandConstant.ATTR_ERROR_MESSAGE, CommandConstant.ERROR_INVALID_PARAMS);
                router.setPage(page);
                router.setRedirect();
                return router;
            }

            boolean isExists = alienService.checkDuplicate(name, lor);
            LOGGER.debug(48 + " Add Alien");
            if (isExists) {
                request.setAttribute(CommandConstant.ATTR_ERROR_MESSAGE, CommandConstant.ERROR_DUPLICATE_ALIEN);
            //} else if (alienService.addNewCharacter(alien)) {
            } else if (true) {
                LOGGER.debug(52 + " Add Alien");
                List<Alien> aliens = alienService.fetchListOfCharacters();
                LOGGER.debug("Aliens size: " + aliens.size());
                request.setAttribute(CommandConstant.ATTR_ALIENS_LIST, aliens);
            } else {
                request.setAttribute(CommandConstant.ATTR_ERROR_MESSAGE, CommandConstant.ERROR_ALIEN_SAVE_FAILED);
            }

            router.setPage(page);
            //router.setRedirect();
            return router;
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
