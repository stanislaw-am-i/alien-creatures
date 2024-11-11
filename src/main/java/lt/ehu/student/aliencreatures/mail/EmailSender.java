package lt.ehu.student.aliencreatures.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private static final Logger LOGGER = LogManager.getLogger(EmailSender.class);
    private static final String SET_CONTENT_TYPE = "text/html";

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public EmailSender(String sendToEmail, EmailContent emailContent, Properties props) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = emailContent.getSubject();
        this.mailText = emailContent.getBody();
        this.properties = props;
    }

    public void send() {
        try {
            initMessage();
            Transport.send(message);// sending mail
        } catch (AddressException e) {
            LOGGER.warn("Invalid address: {} {}", sendToEmail, e);
        } catch (MessagingException e) {
            LOGGER.warn("Error generating or sending message: {}", e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, SET_CONTENT_TYPE);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

}