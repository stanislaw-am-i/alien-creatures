package lt.ehu.student.aliencreatures.command;

public class CommandConstant {

    // JSP Page paths
    public static final String ALIENS_PAGE = "jsp/aliens.jsp";
    public static final String MAIN_PAGE = "jsp/main.jsp";
    public static final String LOGIN_PAGE = "jsp/login.jsp";
    public static final String INDEX_PAGE = "index.jsp";
    public static final String SIGNUP_PAGE = "jsp/signup.jsp";
    public static final String CONFIRM_REGISTRATION_PAGE = "jsp/confirm_registration.jsp";

    // Error messages
    public static final String ERROR_INVALID_PARAMS = "Provided params are not valid";
    public static final String ERROR_DUPLICATE_ALIEN = "This alien has already been added";
    public static final String ERROR_ALIEN_SAVE_FAILED = "Failed to save the alien.";
    public static final String ERROR_LOGIN_INCORRECT = "Incorrect login";
    public static final String ERROR_REGISTRATION_MESSAGE = "errorRegistrationMessage";

    // Success messages
    public static final String CONFIRM_REG_SUCCESS_MESSAGE = "Your account is activated. You can login now.";
    public static final String CONFIRM_REG_ERROR_MESSAGE = "Your account wasn't activated.";

    // Request attributes
    public static final String ATTR_ERROR_MESSAGE = "errorPassMessage";
    public static final String ATTR_ERROR_LOGIN_PASS_MESSAGE = "errorLoginPassMessage";
    public static final String ATTR_CONFIRM_REG_SUCCESS_MESSAGE = "confirmationMessage";
    public static final String ATTR_CONFIRM_REG_ERROR_MESSAGE = "errorRegistrationMessage";
    public static final String ATTR_ALIENS_LIST = "aliensList";
    public static final String ATTR_USER = "user";
    public static final String ATTR_USER_NAME = "user_name";
    public static final String ATTR_CURRENT_PAGE = "current_page";

    // Request parameters
    public static final String NAME_PARAM = "name";
    public static final String LOR_PARAM = "lor";
    public static final String LOGIN_PARAM = "login";
    public static final String PASSWORD_PARAM = "password";
    public static final String USERNAME_PARAM = "username";
    public static final String EMAIL_PARAM = "email";
    public static final String CODE_PARAM = "code";

    // Command exception
    public static final String FAILED_TO_LOGIN_EXP = "Failed to login";

    private CommandConstant() {}
}
