package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandConstant;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.exception.ServiceException;
import lt.ehu.student.aliencreatures.service.UserService;
import lt.ehu.student.aliencreatures.service.impl.UserServiceImpl;

public class ConfirmRegistrationCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(CommandConstant.CONFIRM_REGISTRATION_PAGE);
        try {
            String code = request.getParameter(CommandConstant.CODE_PARAM);
            boolean isActivated = userService.activateRegistration(code);
            if (isActivated) {
                request.setAttribute(CommandConstant.ATTR_CONFIRM_REG_SUCCESS_MESSAGE, CommandConstant.CONFIRM_REG_SUCCESS_MESSAGE);
            } else {
                System.out.println(CommandConstant.CONFIRM_REG_ERROR_MESSAGE);
                request.setAttribute(CommandConstant.ATTR_CONFIRM_REG_ERROR_MESSAGE, CommandConstant.CONFIRM_REG_ERROR_MESSAGE);
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            request.setAttribute(CommandConstant.ATTR_CONFIRM_REG_ERROR_MESSAGE, e.getMessage());
        }

        return router;
    }
}
