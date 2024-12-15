package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandConstant;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.service.impl.UserServiceImpl;

public class ResendConfirmationMailCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = CommandConstant.NOTIFICATION_PAGE;
        String email = (String) session.getAttribute(CommandConstant.ATTR_USER_EMAIL);
        String login = (String) session.getAttribute(CommandConstant.ATTR_USER_NAME);
        String instanceUrl = request.getRequestURL().toString();
        try {
            userService.sendEmailToVerifyUser(login, email, instanceUrl);
            request.setAttribute("unconfirmedRegistrationResend", true);
            return new Router(page);
        } catch (ServiceException e) {
            // todo: log debug
            throw new CommandException(e);
        }
    }
}
