package lt.ehu.student.aliencreatures.service;

public class ServiceConstant {
    // Exception messages
    public static final String INVALID_USERNAME_EXP = "Invalid username.";
    public static final String INVALID_EMAIL_FORMAT_EXP = "Invalid email format.";
    public static final String INVALID_PASSWORD_EXP = "Password must be at least 8 characters long.";
    public static final String USER_ALREADY_EXISTS_EXP = "User Already Exists.";
    public static final String FIND_USER_BY_USERNAME_FAILED_EXP = "Failed to find user by username.";
    public static final String FIND_USER_BY_EMAIL_FAILED_EXP = "Failed to find user by email.";
    public static final String SAVE_USER_FAILED_EXP = "Failed to save new user.";

    // Other
    public static final String CONFIRM_REGISTRATION_URL = "?command=CONFIRM_REGISTRATION&code=";

    private ServiceConstant() {}
}
