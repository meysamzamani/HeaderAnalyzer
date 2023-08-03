package com.meysamzamani.headeranalyzer.service;

import com.meysamzamani.headeranalyzer.config.ApplicationConfig;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.IOException;
import java.util.Properties;

/**
 * The MailService class provides functionality for sending emails using the JakartaMail API.
 * It follows the Singleton design pattern to ensure only one instance of the MailService is available throughout the application.
 */
public class MailService {

    /**
     * The single instance of the MailService class.
     */
    private static MailService mailService;

    /**
     * The JakartaMail session used to send emails.
     */
    private final Session session;

    /**
     * The configuration properties for the email service.
     */
    private final Properties properties;


    /**
     * Private constructor for the MailService class to prevent external instantiation.
     * It loads the email service configuration properties from the ApplicationConfig and sets up the JakartaMail session.
     *
     * @throws IOException if an I/O error occurs while reading the configuration properties.
     */
    private MailService() throws IOException {
        properties = ApplicationConfig.getInstance().getConfigs();
        session = getSessionInstance();
    }

    /**
     * Gets the single instance of the MailService class. If the instance does not exist, a new instance is created.
     *
     * @return the single instance of the MailService class.
     * @throws IOException if an I/O error occurs while reading the configuration properties.
     */
    public static MailService getInstance() throws IOException {
        if (mailService == null) {
            mailService = new MailService();
        }
        return mailService;
    }

    /**
     * Creates and returns a Properties object containing the email service configuration settings.
     *
     * @return a Properties object containing the email service configuration settings.
     */
    private Properties getConfiguration() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", properties.getProperty("tls_enable"));
        prop.put("mail.smtp.host", properties.getProperty("host"));
        prop.put("mail.smtp.port", properties.getProperty("port"));
        prop.put("mail.smtp.ssl.trust", properties.getProperty("host"));
        return prop;
    }

    /**
     * Creates and returns a JakartaMail session using the configuration settings and authentication.
     *
     * @return a JakartaMail session for sending emails.
     */
    private Session getSessionInstance() {
        return Session.getInstance(getConfiguration(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password"));
            }
        });
    }

    /**
     * Sends an email with the specified recipient, message content, and subject.
     *
     * @param to      the recipient's email address.
     * @param msg     the content of the email in HTML format.
     * @param subject the subject of the email.
     * @throws Exception if an error occurs while sending the email.
     */
    public void sendMail(String to, String msg, String subject) throws Exception {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(properties.getProperty("from")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
