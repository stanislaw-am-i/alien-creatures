package lt.ehu.student.aliencreatures.controller;

public final class Parameter {
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
    public static final String ATTR_USER_EMAIL = "user_email";
    public static final String ATTR_USER_IS_ACTIVE = "is_active";
    public static final String ATTR_CURRENT_PAGE = "current_page";
    public static final String ATTR_SUCCESS_MESSAGE = "isSuccessMessage";

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

    // Error pages
    public static final String ERROR_500_PAGE = "jsp/error/error_500.jsp";

    // Error attributes
    public static final String ATTR_ERR_MESSAGE = "error_msg";

    // Request parameters
    public static final String COMMAND_PARAM = "command";

    // Content types
    public static final String CONTENT_TYPE_HTML = "text/html";

    public static final String INVALID_USERNAME_EXP = "Invalid username.";
    public static final String INVALID_EMAIL_FORMAT_EXP = "Invalid email format.";
    public static final String INVALID_PASSWORD_EXP = "Password must be at least 8 characters long.";
    public static final String USER_ALREADY_EXISTS_EXP = "User Already Exists.";
    public static final String FIND_USER_BY_USERNAME_FAILED_EXP = "Failed to find user by username.";
    public static final String FIND_USER_BY_EMAIL_FAILED_EXP = "Failed to find user by email.";
    public static final String SAVE_USER_FAILED_EXP = "Failed to save new user.";

    // Other
    public static final String CONFIRM_REGISTRATION_URL = "?command=CONFIRM_REGISTRATION&code=";

    private Parameter() {}
}
