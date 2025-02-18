package com.utils;

import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.config.ConfigReader;

public class EmailSender {
    public static void main(String[] args) {

        // Fetch email settings
        String smtpServer = ConfigReader.getProperty("smtp.server");
        String smtpPort = ConfigReader.getProperty("smtp.port");
        final String smtpUser = ConfigReader.getProperty("smtp.username");
        final String smtpPass = ConfigReader.getProperty("smtp.password");
        String fromEmail = ConfigReader.getProperty("email.from");
        String toEmail = ConfigReader.getProperty("email.recipients");
        String subject = ConfigReader.getProperty("email.subject");
        String body = ConfigReader.getProperty("email.body");

        if (smtpServer == null || smtpPort == null || smtpUser == null || smtpPass == null || fromEmail == null || toEmail == null) {
            System.err.println("❌ Missing required email configuration in application.ConfigReader.");
            return;
        }

        // Email ConfigReader
        Properties mailProps = new Properties();
        mailProps.put("mail.smtp.host", smtpServer);
        mailProps.put("mail.smtp.port", smtpPort);
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.starttls.enable", "true");

        // Create session
        Session session = Session.getInstance(mailProps, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUser, smtpPass);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            // Send email
            Transport.send(message);
            System.out.println("✅ Email sent successfully to " + toEmail);
        } catch (AuthenticationFailedException e) {
            System.err.println("❌ Authentication failed: Please check your SMTP username and password.");
            e.printStackTrace();
        } catch (MessagingException e) {
            System.err.println("❌ Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
