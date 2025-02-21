package com.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.config.ConfigReader;
import com.config.YamlConfigReader;

public class EmailSender {
    protected static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.error("❌ No report link provided. Usage: java EmailSender <REPORT_LINK>");
            return;
        }

        String reportLink = args[0]; // Get report link from command-line argument
        String fromEmail = YamlConfigReader.getSmtpUsername();
        String toEmails = ConfigReader.getEmailTo();
        String subject = ConfigReader.getEmailSubject();

        // Extract sender and recipient names from email addresses
        String senderName = extractName(fromEmail);
        String recipientName = extractName(toEmails.split(",")[0]); // Only first recipient for greeting

        // Format email body with dynamic placeholders
        String body = ConfigReader.getEmailBody()
                .replace("{REPORT_URL}", reportLink)
                .replace("{TO_NAME}", recipientName)
                .replace("{SENDER_NAME}", senderName);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", YamlConfigReader.getSmtpServer());
        props.put("mail.smtp.port", YamlConfigReader.getSmtpPort());

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, YamlConfigReader.getSmtpPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmails));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            Transport.send(message);
            logger.info("✅ Email sent successfully via SMTP to " + toEmails);
        } catch (MessagingException e) {
            logger.error("❌ Failed to send email via SMTP: " + e.getMessage());
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
