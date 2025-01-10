package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.controller.PagePath;
import lt.ehu.student.aliencreatures.controller.Parameter;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class SignUpCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(Parameter.USERNAME_PARAM);
        String email = request.getParameter(Parameter.EMAIL_PARAM);
        String password = request.getParameter(Parameter.PASSWORD_PARAM);
        String page = PagePath.SIGNUP_PAGE;

        try {
            Map<String, String> params = new HashMap<>();
            params.put("login", login);
            params.put("email", email);
            params.put("password", password);
            if (userService.signUp(params)) {
                String instanceUrl = request.getRequestURL().toString();
                userService.sendEmailToVerifyUser(login, email, instanceUrl);
                request.setAttribute(Parameter.ATTR_USER, login);
                page = "jsp/confirm_registration.jsp";
            }
            if (params.containsKey("error")) {
                request.setAttribute(Parameter.ERROR_REGISTRATION_MESSAGE, params.get("error"));
            }
        } catch (ServiceException e) {
            request.setAttribute(Parameter.ERROR_REGISTRATION_MESSAGE, e.getMessage());
        }

        return new Router(page);
    }
}
