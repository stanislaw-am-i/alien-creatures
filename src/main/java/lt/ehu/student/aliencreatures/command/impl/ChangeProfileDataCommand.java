package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.controller.PagePath;
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
        String newLogin = request.getParameter(Parameter.USERNAME_PARAM);
        String newEmail = request.getParameter(Parameter.EMAIL_PARAM);

        HttpSession session = request.getSession();
        String oldLogin = (String) session.getAttribute(Parameter.ATTR_USER_NAME);
        int userId = (int) session.getAttribute(Parameter.USER_ID_PARAM);

        Map<String, String> params = new HashMap<>();
        params.put(Parameter.USERNAME_PARAM, newLogin);
        params.put(Parameter.EMAIL_PARAM, newEmail);
        params.put(Parameter.OLD_USERNAME_PARAM, oldLogin);
        params.put(Parameter.USER_ID_PARAM, String.valueOf(userId));

        try {
            Optional<User> userOptional = userService.updateUserProfile(params);
            if (userOptional.isPresent()) {
                session.setAttribute(Parameter.ATTR_USER_NAME, userOptional.get().getUsername());
                session.setAttribute(Parameter.EMAIL_PARAM, userOptional.get().getEmail());
                request.setAttribute(Parameter.ATTR_SUCCESS_MESSAGE, true);
            }
            if (params.containsKey("error")) {
                request.setAttribute(Parameter.ATTR_SHOW_ERR_MESSAGE, params.get("error"));
            }
            return new Router(PagePath.PROFILE_PAGE);
        } catch (ServiceException e) {
            throw new CommandException(Parameter.FAILED_TO_LOGIN_EXP, e);
        }
    }
}
