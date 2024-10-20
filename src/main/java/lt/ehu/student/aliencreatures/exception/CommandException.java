package lt.ehu.student.aliencreatures.exception;

public class CommandException extends Exception {
    public CommandException() {
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(String message) {
        super(message);
    }
}
