package lt.ehu.student.aliencreatures.command;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.exception.CommandException;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
