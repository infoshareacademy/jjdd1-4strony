package com.isacademy.jjdd1.czterystrony.reports.mail;

import com.isacademy.jjdd1.czterystrony.CompanyMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

@Stateless
public class EmailSender {

    private static Logger log = LoggerFactory.getLogger(EmailSender.class);

    public void send(String toEmail, String subject, String htmlContent) {
        try {
            MimeMessage message = new MimeMessage(getSession());
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
            message.setFrom(new InternetAddress(CompanyMail.ADDRESS, "4analysis"));
            message.setReplyTo(InternetAddress.parse(CompanyMail.ADDRESS, false));
            message.setSubject(subject, "UTF-8");
            message.setText(htmlContent, "UTF-8", "html");
            message.setSentDate(new Date());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(message);
            log.info("Email successfully sent to {}", toEmail);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            log.info("Could not send email to {}", toEmail);
        }
    }

    private Session getSession() {
        Properties properties = getProperties();
        Authenticator authenticator = getAuthenticator();
        return Session.getInstance(properties, authenticator);
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

    private Authenticator getAuthenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CompanyMail.ADDRESS, CompanyMail.PASSWORD);
            }
        };
    }
}
