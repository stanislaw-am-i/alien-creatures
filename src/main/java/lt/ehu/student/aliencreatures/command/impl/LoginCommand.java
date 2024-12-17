package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandConstant;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.entity.User;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.page.PaginatedResult;
import lt.ehu.student.aliencreatures.service.AlienService;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.service.impl.AlienServiceImpl;
import lt.ehu.student.aliencreatures.service.impl.UserServiceImpl;

import java.util.Optional;

public class LoginCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();
    private final AlienService alienService = AlienServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(CommandConstant.LOGIN_PARAM);
        String password = request.getParameter(CommandConstant.PASSWORD_PARAM);
        String page;
        HttpSession session = request.getSession();
        try {
            if (userService.authenticate(login, password)) {
                Optional<User> optionalUser = userService.findByUsername(login);
                User user = optionalUser.get();
                // todo: method to set session attributes
                session.setAttribute("currentUserId", user.getId());
                session.setAttribute("userRole", user.getRole().toString());
                switch (user.getStatus()) {
                    case ACTIVE:
                        request.setAttribute(CommandConstant.ATTR_USER, user.getUsername());
                        session.setAttribute(CommandConstant.ATTR_USER_NAME, user.getUsername());
                        session.setAttribute(CommandConstant.ATTR_USER_IS_ACTIVE, true);

                        String pageParam = request.getParameter("page");
                        String pageSizeParam = request.getParameter("pageSize");

                        PaginatedResult<Alien> paginatedResult = alienService.fetchAliensForPage(pageParam, pageSizeParam);
                        request.setAttribute(CommandConstant.ATTR_ALIENS_LIST, paginatedResult.getItems());
                        page = CommandConstant.MAIN_PAGE;
                        break;
                    case INACTIVE:
                        session.setAttribute(CommandConstant.ATTR_USER_NAME, user.getUsername());
                        session.setAttribute(CommandConstant.ATTR_USER_EMAIL, user.getEmail());
                        session.setAttribute(CommandConstant.ATTR_USER_IS_ACTIVE, false);
                        request.setAttribute("unconfirmedRegistration", true);
                        page = CommandConstant.NOTIFICATION_PAGE;
                        break;
                    case BANNED:
                        request.setAttribute("userBanned", true);
                        session.setAttribute(CommandConstant.ATTR_USER_IS_ACTIVE, false);
                        page = CommandConstant.NOTIFICATION_PAGE;
                        break;
                    default:
                        page = CommandConstant.MAIN_PAGE;
                }
                // todo: get aliens
                //page = CommandConstant.MAIN_PAGE;
            } else {
                request.setAttribute(CommandConstant.ATTR_ERROR_LOGIN_PASS_MESSAGE, CommandConstant.ERROR_LOGIN_INCORRECT);
                page = CommandConstant.LOGIN_PAGE;
            }
            session.setAttribute(CommandConstant.ATTR_CURRENT_PAGE, page);
            return new Router(page);
        } catch (ServiceException e) {
            throw new CommandException(CommandConstant.FAILED_TO_LOGIN_EXP, e);
        }
    }
}
