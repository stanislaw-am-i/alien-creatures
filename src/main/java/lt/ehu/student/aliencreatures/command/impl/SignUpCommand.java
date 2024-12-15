package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandConstant;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.service.impl.UserServiceImpl;

public class SignUpCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(CommandConstant.USERNAME_PARAM);
        String email = request.getParameter(CommandConstant.EMAIL_PARAM);
        String password = request.getParameter(CommandConstant.PASSWORD_PARAM);
        String page;

        try {
            // todo: handle registration confirmation
            if (userService.signUp(login, email, password)) {
                String instanceUrl = request.getRequestURL().toString();
                userService.sendEmailToVerifyUser(login, email, instanceUrl);
            }
            request.setAttribute(CommandConstant.ATTR_USER, login);
            page = "jsp/confirm_registration.jsp";
        } catch (ServiceException e) {
            request.setAttribute(CommandConstant.ERROR_REGISTRATION_MESSAGE, e.getMessage());
            page = CommandConstant.SIGNUP_PAGE;
        }

        return new Router(page);
    }
}
