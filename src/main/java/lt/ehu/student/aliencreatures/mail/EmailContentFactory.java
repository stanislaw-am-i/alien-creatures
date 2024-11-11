package lt.ehu.student.aliencreatures.mail;

import lt.ehu.student.aliencreatures.mail.impl.GreetingNewUserEmailContentImpl;
import lt.ehu.student.aliencreatures.mail.impl.RegistrationConfirmEmailContentImpl;

public class EmailContentFactory {

    public static EmailContent createEmailContent(EmailType emailType, String userName, String url) {
        switch (emailType) {
            case REGISTRATION_CONFIRMATION:
                return new RegistrationConfirmEmailContentImpl(userName, url);
            case GREETING_NEW_USER:
                return new GreetingNewUserEmailContentImpl(userName, url);
            default:
                throw new IllegalArgumentException("Invalid email type");
        }
    }
}
