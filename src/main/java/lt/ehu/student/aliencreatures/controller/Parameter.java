package lt.ehu.student.aliencreatures.controller;

public final class Parameter {

    public static final String ERROR_INVALID_PARAMS = "Provided params are not valid";
    public static final String ERROR_DUPLICATE_ALIEN = "This alien has already been added";
    public static final String ERROR_ALIEN_SAVE_FAILED = "Failed to save the alien.";
    public static final String ERROR_LOGIN_INCORRECT = "Incorrect login";
    public static final String ERROR_REGISTRATION_MESSAGE = "errorRegistrationMessage";

    public static final String CONFIRM_REG_SUCCESS_MESSAGE = "Your account is activated. You can login now.";
    public static final String CONFIRM_REG_ERROR_MESSAGE = "Your account wasn't activated.";

    public static final String ATTR_ERROR_MESSAGE = "errorPassMessage";
    public static final String ATTR_SHOW_ERR_MESSAGE = "errorMessage";
    public static final String ATTR_ERROR_LOGIN_PASS_MESSAGE = "errorLoginPassMessage";
    public static final String ATTR_CONFIRM_REG_SUCCESS_MESSAGE = "confirmationMessage";
    public static final String ATTR_CONFIRM_REG_ERROR_MESSAGE = "errorRegistrationMessage";
    public static final String ATTR_ALIENS_LIST = "aliensList";
    public static final String ATTR_USER = "user";
    public static final String ATTR_USER_NAME = "user_name";
    public static final String ATTR_USER_EMAIL = "user_email";
    public static final String ATTR_USER_IS_ACTIVE = "is_active";
    public static final String ATTR_CURRENT_PAGE = "current_page";
    public static final String ATTR_PAGE = "page";
    public static final String ATTR_PAGE_SIZE = "pageSize";
    public static final String ATTR_ALIEN_ID = "alienId";
    public static final String ATTR_SUCCESS_MESSAGE = "isSuccessMessage";
    public static final String ATTR_CURRENT_USER_ID = "currentUserId";
    public static final String ATTR_USER_ROLE = "userRole";
    public static final String ATTR_CURRENT_PAGE_NUMBER = "currentPage";
    public static final String ATTR_TOTAL_PAGES = "totalPages";
    public static final String ATTR_NOT_CONFIRM_REG = "unconfirmedRegistration";
    public static final String ATTR_NOT_CONFIRM_REG_RESEND = "unconfirmedRegistrationResend";
    public static final String ATTR_USER_BANNED = "userBanned";

    public static final String NAME_PARAM = "name";
    public static final String LOR_PARAM = "lor";
    public static final String LOGIN_PARAM = "login";
    public static final String PASSWORD_PARAM = "password";
    public static final String USERNAME_PARAM = "username";
    public static final String OLD_USERNAME_PARAM = "oldUsername";
    public static final String USER_ID_PARAM = "userId";
    public static final String EMAIL_PARAM = "email";
    public static final String CODE_PARAM = "code";
    public static final String ENCODING_UTF_8_PARAM = "UTF-8";

    public static final String FAILED_TO_LOGIN_EXP = "Failed to login";

    public static final String ERROR_500_PAGE = "jsp/error/error_500.jsp";

    public static final String ATTR_ERR_MESSAGE = "error_msg";

    public static final String COMMAND_PARAM = "command";

    public static final String CONTENT_TYPE_HTML = "text/html";

    public static final String INVALID_USERNAME_EXP = "Invalid username.";
    public static final String INVALID_EMAIL_FORMAT_EXP = "Invalid email format.";
    public static final String INVALID_PASSWORD_EXP = "Password must be at least 8 characters long.";
    public static final String USER_ALREADY_EXISTS_EXP = "User Already Exists.";
    public static final String FIND_USER_BY_USERNAME_FAILED_EXP = "Failed to find user by username.";
    public static final String FIND_USER_BY_EMAIL_FAILED_EXP = "Failed to find user by email.";
    public static final String SAVE_USER_FAILED_EXP = "Failed to save new user.";

    public static final String CONFIRM_REGISTRATION_URL = "?command=CONFIRM_REGISTRATION&code=";

    private Parameter() {}
}
