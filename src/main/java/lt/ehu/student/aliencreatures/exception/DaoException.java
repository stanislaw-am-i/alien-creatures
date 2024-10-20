package lt.ehu.student.aliencreatures.exception;

public class DaoException extends Exception {
    public DaoException() {
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }
}
