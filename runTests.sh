#!/bin/bash

# Exit on error
set -e  

# Navigate to project directory
cd "$(dirname "$0")"

# Load email configuration from application.properties
APP_PROPERTIES="src/main/resources/application.properties"

SMTP_SERVER=$(grep 'smtp.server' "$APP_PROPERTIES" | cut -d'=' -f2)
SMTP_PORT=$(grep 'smtp.port' "$APP_PROPERTIES" | cut -d'=' -f2)
SMTP_USER=$(grep 'smtp.username' "$APP_PROPERTIES" | cut -d'=' -f2)
SMTP_PASS=$(grep 'smtp.password' "$APP_PROPERTIES" | cut -d'=' -f2)
EMAIL_RECIPIENTS=$(grep 'email.recipients' "$APP_PROPERTIES" | cut -d'=' -f2)
EMAIL_SUBJECT=$(grep 'email.subject' "$APP_PROPERTIES" | cut -d'=' -f2)
EMAIL_BODY=$(grep 'email.body' "$APP_PROPERTIES" | cut -d'=' -f2)

# Generate Allure Report
echo "Generating Allure report..."
allure generate --clean allure-results -o allure-report

# Define the Report URL (for Jenkins)
JENKINS_URL="http://your-jenkins-server/job/sample/allure-report"
EMAIL_BODY=$(echo "$EMAIL_BODY" | sed "s|{REPORT_URL}|$JENKINS_URL|g")

# Ensure sendmail or PowerShell is available
if ! command -v sendmail &> /dev/null; then
    echo "sendmail not found. Using PowerShell..."

    # Use PowerShell for Windows
    powershell.exe -Command "
    \$SMTPServer = '$SMTP_SERVER';
    \$SMTPPort = $SMTP_PORT;
    \$SMTPUser = '$SMTP_USER';
    \$SMTPPass = '$SMTP_PASS';
    \$MailMessage = New-Object System.Net.Mail.MailMessage;
    \$MailMessage.From = '$SMTP_USER';
    \$MailMessage.To.Add('$EMAIL_RECIPIENTS');
    \$MailMessage.Subject = '$EMAIL_SUBJECT';
    \$MailMessage.IsBodyHtml = \$true;
    \$MailMessage.Body = '<html><body>$EMAIL_BODY</body></html>';
    \$SMTPClient = New-Object Net.Mail.SmtpClient(\$SMTPServer, \$SMTPPort);
    \$SMTPClient.EnableSsl = \$true;
    \$SMTPClient.Credentials = New-Object System.Net.NetworkCredential(\$SMTPUser, \$SMTPPass);
    \$SMTPClient.Send(\$MailMessage);
    "
else
    echo -e "Subject: $EMAIL_SUBJECT\nMIME-Version: 1.0\nContent-Type: text/html\n\n$EMAIL_BODY" | sendmail -v $EMAIL_RECIPIENTS
fi

echo "Email sent successfully to $EMAIL_RECIPIENTS"
