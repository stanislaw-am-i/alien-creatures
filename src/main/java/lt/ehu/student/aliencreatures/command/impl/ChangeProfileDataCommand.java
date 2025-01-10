package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.controller.Parameter;
import lt.ehu.student.aliencreatures.entity.User;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangeProfileDataCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String newLogin = request.getParameter("username");
        String newEmail = request.getParameter("email");

        HttpSession session = request.getSession();
        String oldLogin = (String) session.getAttribute(Parameter.ATTR_USER_NAME);
        int userId = (int) session.getAttribute("userId");

        Map<String, String> params = new HashMap<>();
        params.put("username", newLogin);
        params.put("email", newEmail);
        params.put("oldUsername", oldLogin);
        params.put("userId", String.valueOf(userId));
        System.out.println(oldLogin);
        try {
            Optional<User> userOptional = userService.updateUserProfile(params);
            if (userOptional.isPresent()) {
                session.setAttribute(Parameter.ATTR_USER_NAME, userOptional.get().getUsername());
                session.setAttribute("email", userOptional.get().getEmail());
                request.setAttribute(Parameter.ATTR_SUCCESS_MESSAGE, true);
            }
            if (params.containsKey("error")) {
                request.setAttribute("errorMessage", params.get("error"));
            }
            return new Router( "jsp/profile.jsp");
        } catch (ServiceException e) {
            throw new CommandException(Parameter.FAILED_TO_LOGIN_EXP, e);
        }
    }
}
