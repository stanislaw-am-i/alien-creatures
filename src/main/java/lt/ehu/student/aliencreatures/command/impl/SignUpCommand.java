package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.service.impl.UserServiceImpl;

public class SignUpCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String page;

        try {
            userService.signUp(login, email, password);
            request.setAttribute("user", login);
            page = "jsp/main.jsp";
        } catch (RuntimeException e) {
            request.setAttribute("errorRegistrationMessage", e.getMessage());
            page = "jsp/signup.jsp";
        }

        return page;
    }
}
