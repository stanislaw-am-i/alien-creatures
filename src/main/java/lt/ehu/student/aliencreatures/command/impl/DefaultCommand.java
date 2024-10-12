package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "";
    }
}
