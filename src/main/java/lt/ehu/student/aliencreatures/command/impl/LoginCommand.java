package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.controller.PagePath;
import lt.ehu.student.aliencreatures.controller.Parameter;
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
        String login = request.getParameter(Parameter.LOGIN_PARAM);
        String password = request.getParameter(Parameter.PASSWORD_PARAM);
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
                        request.setAttribute(Parameter.ATTR_USER, user.getUsername());
                        session.setAttribute(Parameter.ATTR_USER_NAME, user.getUsername());
                        session.setAttribute(Parameter.ATTR_USER_IS_ACTIVE, true);
                        session.setAttribute("email", user.getEmail());
                        session.setAttribute("userId", user.getId());

                        String pageParam = request.getParameter("page");
                        String pageSizeParam = request.getParameter("pageSize");

                        PaginatedResult<Alien> paginatedResult = alienService.fetchAliensForPage(pageParam, pageSizeParam);
                        request.setAttribute(Parameter.ATTR_ALIENS_LIST, paginatedResult.getItems());
                        page = PagePath.MAIN_PAGE;
                        break;
                    case INACTIVE:
                        session.setAttribute(Parameter.ATTR_USER_NAME, user.getUsername());
                        session.setAttribute(Parameter.ATTR_USER_EMAIL, user.getEmail());
                        session.setAttribute(Parameter.ATTR_USER_IS_ACTIVE, false);
                        request.setAttribute("unconfirmedRegistration", true);
                        page = PagePath.NOTIFICATION_PAGE;
                        break;
                    case BANNED:
                        request.setAttribute("userBanned", true);
                        session.setAttribute(Parameter.ATTR_USER_IS_ACTIVE, false);
                        page = PagePath.NOTIFICATION_PAGE;
                        break;
                    default:
                        page = PagePath.MAIN_PAGE;
                }
            } else {
                request.setAttribute(Parameter.ATTR_ERROR_LOGIN_PASS_MESSAGE, Parameter.ERROR_LOGIN_INCORRECT);
                page = PagePath.LOGIN_PAGE;
            }
            session.setAttribute(Parameter.ATTR_CURRENT_PAGE, page);
            return new Router(page);
        } catch (ServiceException e) {
            throw new CommandException(Parameter.FAILED_TO_LOGIN_EXP, e);
        }
    }
}
