package com.isacademy.jjdd1.czterystrony.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Stateless
public class EmailUtility {

    private static Logger log = LoggerFactory.getLogger(EmailUtility.class);

    public void sendEmail(String toEmail, String subject, String body) {
        try {
            MimeMessage message = new MimeMessage(getSession());
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
            message.setFrom(new InternetAddress("noreply@4analysis.com", "4analysis_noreply"));
            message.setReplyTo(InternetAddress.parse("noreply@4analysis.com", false));
            message.setSubject(subject, "UTF-8");
            message.setText(body, "UTF-8");
            message.setSentDate(new Date());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(message);
            log.info("Email sent successfully to {}", toEmail);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Could not send email to {}", toEmail);
        }
    }

    private Session getSession() {
        String fromEmail = "4analysisteam@gmail.com";
        String password = "4analysis";
        Properties properties = getProperties();
        Authenticator authenticator = getAuthenticator(fromEmail, password);
        return Session.getDefaultInstance(properties, authenticator);
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    private Authenticator getAuthenticator(String fromEmail, String password) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
    }
}
