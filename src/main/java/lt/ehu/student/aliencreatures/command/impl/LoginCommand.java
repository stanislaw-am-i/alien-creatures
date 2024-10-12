package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.service.impl.UserServiceImpl;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl(); // todo
        String page;
        if (userService.authenticate(login, password)) {
            request.setAttribute("user", login);
            page = "jsp/main.jsp";
        } else {
            request.setAttribute("errorLoginPassMessage", "Incorrect login");
            page = "jsp/login.jsp";
        }
        return page;
    }
}
