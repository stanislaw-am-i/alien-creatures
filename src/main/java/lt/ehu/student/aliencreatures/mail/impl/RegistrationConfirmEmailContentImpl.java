package lt.ehu.student.aliencreatures.mail.impl;

import lt.ehu.student.aliencreatures.mail.EmailContent;

public class RegistrationConfirmEmailContentImpl implements EmailContent {
    private final String userName;
    private final String confirmationUrl;

    public RegistrationConfirmEmailContentImpl(String userName, String confirmationUrl) {
        this.userName = userName;
        this.confirmationUrl = confirmationUrl;
    }

    @Override
    public String getSubject() {
        return "Please Confirm Your Registration";
    }

    @Override
    public String getBody() {
        return String.format(
                "Dear %s,<br/><br/>" +
                        "Thank you for registering with us! Please click the link below to confirm your registration:<br/><br/>" +
                        "<a href=\"%s\">Confirm your email</a><br/><br/>" +
                        "If you didn't register, please ignore this message.<br/><br/>" +
                        "Best regards,<br/>" +
                        "The Team",
                userName, confirmationUrl
        );
    }
}
