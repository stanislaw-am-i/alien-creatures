package lt.ehu.student.aliencreatures.validator.impl;

import lt.ehu.student.aliencreatures.dao.impl.UserDaoImpl;
import lt.ehu.student.aliencreatures.validator.Validator;

import java.util.regex.Pattern;

public class ValidatorImpl implements Validator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9]{3,20}$");

    private static ValidatorImpl instance = new ValidatorImpl();

    private ValidatorImpl() {}

    public static ValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public boolean validatePassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    @Override
    public boolean validateUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    @Override
    public boolean validateNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
