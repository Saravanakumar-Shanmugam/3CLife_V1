#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e  

# Navigate to the project directory
cd "$(dirname "$0")"

# Load email configuration from application.properties (located in src/main/resources)
APP_PROPERTIES="src/main/resources/application.properties"

# Load email details from application.properties
EMAIL_RECIPIENTS=$(grep 'email.recipients' "$APP_PROPERTIES" | cut -d'=' -f2)
EMAIL_SUBJECT=$(grep 'email.subject' "$APP_PROPERTIES" | cut -d'=' -f2)
EMAIL_BODY=$(grep 'email.body' "$APP_PROPERTIES" | cut -d'=' -f2)

# SMTP configuration from application.properties
SMTP_SERVER=$(grep 'smtp.server' "$APP_PROPERTIES" | cut -d'=' -f2)
SMTP_PORT=$(grep 'smtp.port' "$APP_PROPERTIES" | cut -d'=' -f2)
SMTP_USER=$(grep 'smtp.username' "$APP_PROPERTIES" | cut -d'=' -f2)
SMTP_PASS=$(grep 'smtp.password' "$APP_PROPERTIES" | cut -d'=' -f2)

# Run Playwright tests
echo "Running Playwright tests..."
mvn clean test -Dsurefire.failIfNoTests=false -DtestFailureIgnore=true    

# Verify if test results exist
if [ ! -d "allure-results" ] || [ -z "$(ls -A allure-results)" ]; then
    echo "Error: No Allure test results found!"
    exit 1
fi

# Generate Allure Report
echo "Generating Allure report..."
allure generate --clean allure-results -o allure-report

# Define the Report URL (hosted via Jenkins)
JENKINS_URL="http://localhost:8080/job/sample/allure-report"

# Replace {REPORT_URL} placeholder in email body
EMAIL_BODY=$(echo "$EMAIL_BODY" | sed "s|{REPORT_URL}|$JENKINS_URL|g")

# If sendmail is missing, use PowerShell to send email (for Windows compatibility)
if ! command -v sendmail &> /dev/null; then
    echo "sendmail not found. Using PowerShell..."

    powershell.exe -Command "
    \$SMTPServer = '$SMTP_SERVER';
    \$SMTPPort = $SMTP_PORT;
    \$SMTPUser = '$SMTP_USER';
    \$SMTPPass = ConvertTo-SecureString '$SMTP_PASS' -AsPlainText -Force;
    \$MailMessage = New-Object System.Net.Mail.MailMessage;
    \$MailMessage.From = '$SMTP_USER';
    \$MailMessage.To.Add('$EMAIL_RECIPIENTS');
    \$MailMessage.Subject = '$EMAIL_SUBJECT';
    \$MailMessage.IsBodyHtml = \$true;
    \$MailMessage.Body = '$EMAIL_BODY';
    \$SMTPClient = New-Object Net.Mail.SmtpClient(\$SMTPServer, \$SMTPPort);
    \$SMTPClient.EnableSsl = \$true;
    \$SMTPClient.Credentials = New-Object System.Net.NetworkCredential(\$SMTPUser, \$SMTPPass);
    \$SMTPClient.Send(\$MailMessage);
    " || { echo "PowerShell email sending failed!"; exit 1; }
else
    # If sendmail is available (Linux/macOS)
    echo -e "Subject: $EMAIL_SUBJECT\nMIME-Version: 1.0\nContent-Type: text/html\n\n$EMAIL_BODY" | sendmail -v $EMAIL_RECIPIENTS
fi

echo "Email sent successfully to $EMAIL_RECIPIENTS"
