package lt.ehu.student.aliencreatures.command;

import lt.ehu.student.aliencreatures.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    ADD_ALIEN(new AddAlienCommand()),
    DELETE_ALIEN(new DeleteAlienCommand()),
    SHOW_ALIEN(new ShowAlienCommand()),
    SIGN_UP(new SignUpCommand()),
    CHANGE_PROFILE_DATA(new ChangeProfileDataCommand()),
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
        if (commandStr == null || commandStr.isBlank()) {
            return CommandType.DEFAULT.getCommand();
        }
        return CommandType.valueOf(commandStr.toUpperCase()).getCommand();
    }
}
