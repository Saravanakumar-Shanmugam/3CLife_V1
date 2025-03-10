package com.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.config.ConfigReader;

public class EmailSender {
    protected static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.error("No report link provided. Usage: java EmailSender <REPORT_LINK>");
            return;
        }

        String reportLink = args[0];
        String fromEmail = ConfigReader.getEmailFrom();
        String[] toEmails = ConfigReader.getEmailTo().split(",");
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String subject = ConfigReader.getEmailSubject().replace("${CURRENT_DATE}", currentDate);
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", System.getenv("SMTP_SERVER"));
        props.put("mail.smtp.port", System.getenv("SMTP_PORT"));

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getenv("SMTP_USERNAME"), System.getenv("SMTP_PASSWORD"));
            }
        });

        for (String toEmail : toEmails) {
            try {
                String recipientName = extractName(toEmail.trim()); // Get name for each recipient
                String emailBody = ConfigReader.getEmailBody()
                        .replace("${CURRENT_DATE}", currentDate)
                        .replace("{REPORT_URL}", reportLink)
                        .replace("{TO_NAME}", recipientName)
                        .replace("{SENDER_NAME}", extractName(fromEmail));

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(fromEmail));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail.trim()));
                message.setSubject(subject);
                message.setContent(emailBody, "text/html");

                Transport.send(message);
                logger.info("Email sent successfully to: " + toEmail.trim());
            } catch (MessagingException e) {
                logger.error("Failed to send email to: " + toEmail.trim() + " - " + e.getMessage());
            }
        }
    }


    // Extract name from email address (e.g., "john.doe@example.com" → "John Doe")
    private static String extractName(String email) {
        if (email == null || !email.contains("@")) return "User";
        String namePart = email.split("@")[0]; // Get part before @
        String formattedName = namePart.replaceAll("[._]", " "); // Replace dots/underscores with spaces
        return capitalizeEachWord(formattedName);
    }

    // Capitalize each word in a name
    private static String capitalizeEachWord(String name) {
        String[] words = name.split(" ");
        StringBuilder formattedName = new StringBuilder();
        for (String word : words) {
            formattedName.append(Character.toUpperCase(word.charAt(0)))
                         .append(word.substring(1))
                         .append(" ");
        }
        return formattedName.toString().trim();
    }
}
