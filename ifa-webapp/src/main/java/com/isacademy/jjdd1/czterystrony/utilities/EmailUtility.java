package com.isacademy.jjdd1.czterystrony.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Stateless
public class EmailUtility {

    private static Logger log = LoggerFactory.getLogger(EmailUtility.class);

    public void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
            message.setFrom(new InternetAddress("4analysisteam@gmail.com", "4analysis"));
            message.setReplyTo(InternetAddress.parse("4analysisteam@gmail.com", false));
            message.setSubject(subject, "UTF-8");
            message.setText(body, "UTF-8");
            message.setSentDate(new Date());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(message);
            log.info("Email sent successfully to {}", toEmail);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.info("Could not send email to {}", toEmail);
        }
    }
}
