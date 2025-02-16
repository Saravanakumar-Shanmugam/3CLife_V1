package com.utils;

import java.io.File;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailReportSender {
    public static void main(String[] args) {
        final String senderEmail = "nicesaravana97@gmail.com";
        final String senderPassword = "SIVSaravana@97";
        final String recipientEmail = "saravan@ilife.tech";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Allure Test Report");

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hello,\n\nPlease find the attached Allure Test Report.\n\nBest Regards,\nQA Team");

            // Define the correct report path
            String projectDir = System.getProperty("user.dir"); // Get project directory dynamically
            String filePath = projectDir + "/target/allure-report/allure-maven.html";
            File reportFile = new File(filePath);

            // Check if the report file exists
            if (!reportFile.exists()) {
                System.err.println("Error: Report file not found at: " + filePath);
                return;
            }

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(reportFile);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("✅ Allure report sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
