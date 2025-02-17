#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e  

# Navigate to the project directory
cd "$(dirname "$0")"

# Load email configuration from application.properties (located in src/main/resources)
APP_PROPERTIES="src/main/resources/application.properties"

EMAIL_RECIPIENTS=$(grep 'email.recipients' "$APP_PROPERTIES" | cut -d'=' -f2)
EMAIL_SUBJECT=$(grep 'email.subject' "$APP_PROPERTIES" | cut -d'=' -f2)
EMAIL_BODY=$(grep 'email.body' "$APP_PROPERTIES" | cut -d'=' -f2)

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
JENKINS_URL="http://your-jenkins-server/job/sample/allure-report"

# Replace {REPORT_URL} placeholder in email body
EMAIL_BODY=$(echo "$EMAIL_BODY" | sed "s|{REPORT_URL}|$JENKINS_URL|g")

# Ensure `sendmail` is installed (if Linux/macOS)
if ! command -v sendmail &> /dev/null; then
    echo "sendmail not found. Using PowerShell for Windows compatibility..."
    
    # PowerShell Email Sending
    powershell.exe -Command "
    try {
        Write-Host 'Connecting to SMTP server: $SMTPServer on port $SMTPPort'
        \$SMTPClient = New-Object Net.Mail.SmtpClient('$SMTPServer', '$SMTPPort')
        Write-Host 'SMTP client created'

        \$SMTPClient.EnableSsl = $true  # Ensure SSL is enabled
        \$SMTPClient.Credentials = New-Object System.Net.NetworkCredential('$SMTPUser', '$SMTPPass')

        Write-Host 'Sending email...'
        \$MailMessage = New-Object System.Net.Mail.MailMessage
        \$MailMessage.From = '$SMTPUser'
        \$MailMessage.To.Add('$EMAIL_RECIPIENTS')
        \$MailMessage.Subject = '$EMAIL_SUBJECT'
        \$MailMessage.IsBodyHtml = \$true
        \$MailMessage.Body = '<html><body>$EMAIL_BODY</body></html>'

        \$SMTPClient.Send(\$MailMessage)
        Write-Host 'Email sent successfully'
    }
    catch {
        Write-Host 'Failed to send email: $_'
        throw $_  # Rethrow the error for Jenkins to capture
    }
    "
else
    # Linux or macOS: Send email using sendmail
    echo -e "Subject: $EMAIL_SUBJECT\nMIME-Version: 1.0\nContent-Type: text/html\n\n$EMAIL_BODY" | sendmail -v $EMAIL_RECIPIENTS
fi

echo "Email sent successfully to $EMAIL_RECIPIENTS"
