package lt.ehu.student.aliencreatures.mail.impl;

import lt.ehu.student.aliencreatures.mail.EmailContent;

public class GreetingNewUserEmailContentImpl implements EmailContent {
    private final String userName;
    private final String instanceUrl;

    public GreetingNewUserEmailContentImpl(String userName, String instanceUrl) {
        this.userName = userName;
        this.instanceUrl = instanceUrl;
    }

    @Override
    public String getSubject() {
        return "Welcome to Alien Creatures, " + userName + "!";
    }

    @Override
    public String getBody() {
        return String.format(
                "Hello %s,\n\n" +
                        "Welcome to Our Service! We're thrilled to have you on board.\n\n" +
                        "To get started, please visit your dashboard at the link below:\n" +
                        "%s\n\n" +
                        "Thank you for joining us!\n\n" +
                        "Best regards,\n" +
                        "The Alien Creatures Team",
                userName, instanceUrl
        );
    }
}
