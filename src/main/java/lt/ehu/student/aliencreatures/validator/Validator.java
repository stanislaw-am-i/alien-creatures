package lt.ehu.student.aliencreatures.validator;

public interface Validator {
    /**
     * Validates the email format.
     * An email is considered valid if it follows a common pattern:
     * must contain an "@" symbol and a domain, with optional periods and characters before the "@".
     * Example: "user@example.com".
     *
     * @param email the email to validate
     * @return true if the email is valid, false otherwise
     */
    boolean validateEmail(String email);

    /**
     * Validates the password format.
     * A password is considered valid if it meets the following criteria:
     * - At least 8 characters long
     * - Contains at least one uppercase letter
     * - Contains at least one lowercase letter
     * - Contains at least one digit
     * - Contains at least one special character (e.g., @, #, $, etc.)
     *
     * @param password the password to validate
     * @return true if the password is valid, false otherwise
     */
    boolean validatePassword(String password);

    /**
     * Validates the username format.
     * A username is considered valid if it meets the following criteria:
     * - Between 3 and 20 characters
     * - Contains only alphanumeric characters (letters and numbers)
     *
     * @param username the username to validate
     * @return true if the username is valid, false otherwise
     */
    boolean validateUsername(String username);

    /**
     * Checks if a string is not empty (non-null and non-blank).
     *
     * @param value the string to check
     * @return true if the string is non-null and non-empty, false otherwise
     */
    boolean validateNotEmpty(String value);
}
