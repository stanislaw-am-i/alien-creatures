package lt.ehu.student.aliencreatures.mail;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SessionFactory {
    public static Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty("mail.user.name");
        String userPassword = configProperties.getProperty("mail.user.password");
        return Session.getDefaultInstance(configProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        System.out.println(userPassword);
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}