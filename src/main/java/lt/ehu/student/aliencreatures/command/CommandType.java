package lt.ehu.student.aliencreatures.command;

import lt.ehu.student.aliencreatures.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    ADD_ALIEN(new AddAlienCommand()),
    SHOW_ALIEN(new ShowAlienCommand()),
    SIGN_UP(new SignUpCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    RESEND_CONFIRMATION_MAIL(new ResendConfirmationMailCommand()),
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
