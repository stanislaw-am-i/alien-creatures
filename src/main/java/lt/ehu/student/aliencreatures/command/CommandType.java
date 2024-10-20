package lt.ehu.student.aliencreatures.command;

import lt.ehu.student.aliencreatures.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    ADD_ALIEN(new AddAlienCommand()),
    SIGN_UP(new SignUpCommand()),
    DEFAULT(new DefaultCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static Command defineCommand(String commandStr) {
        // todo: via stream and add exception handler probably
        return CommandType.valueOf(commandStr.toUpperCase()).getCommand();
    }
}
