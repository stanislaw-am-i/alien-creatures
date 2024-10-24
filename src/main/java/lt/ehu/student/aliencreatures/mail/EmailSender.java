package lt.ehu.student.aliencreatures.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public EmailSender(String sendToEmail, EmailContent emailContent, Properties props) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = emailContent.getSubject();
        this.mailText = emailContent.getBody();
        this.properties = props; // todo: property handler
    }

    public void send() {
        try {
            initMessage();
            Transport.send(message); // sending mail
        } catch (AddressException e) {
            System.err.println("Invalid address: " + sendToEmail + " " + e); // in log
        } catch (MessagingException e) {
            System.err.println("Error generating or sending message: " + e); // in log
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = SessionFactory.createSession(properties); // mail session object
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession); // create a mailing object
        // loading parameters into the mail message object
        message.setSubject(mailSubject); message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

}