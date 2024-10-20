package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.service.impl.UserServiceImpl;

public class LoginCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String page;
        try {
            if (userService.authenticate(login, password)) {
                request.setAttribute("user", login);
                page = "jsp/main.jsp";
            } else {
                request.setAttribute("errorLoginPassMessage", "Incorrect login");
                page = "jsp/login.jsp";
            }
        } catch (ServiceException e) {
            throw new CommandException("Failed to login", e);
        }
        return page;
    }
}
