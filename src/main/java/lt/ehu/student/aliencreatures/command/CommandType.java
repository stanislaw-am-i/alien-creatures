package lt.ehu.student.aliencreatures.command;

import lt.ehu.student.aliencreatures.command.impl.DefaultCommand;
import lt.ehu.student.aliencreatures.command.impl.LogOutCommand;
import lt.ehu.student.aliencreatures.command.impl.LoginCommand;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    DEFAULT(new DefaultCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static Command defineCommand(String commandStr) {
        // todo via stream and add exception handler
        return CommandType.valueOf(commandStr.toUpperCase()).getCommand();
    }
}
