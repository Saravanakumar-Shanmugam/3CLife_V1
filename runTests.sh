#!/bin/bash

set -e  

cd "$(dirname "$0")"

# Load email configuration from application.properties (located in src/main/resources)
APP_PROPERTIES="src/main/resources/application.properties"

EMAIL_RECIPIENTS=$(grep 'email.recipients' "$APP_PROPERTIES" | cut -d'=' -f2)
EMAIL_SUBJECT=$(grep 'email.subject' "$APP_PROPERTIES" | cut -d'=' -f2)
EMAIL_BODY=$(grep 'email.body' "$APP_PROPERTIES" | cut -d'=' -f2)

# Run Playwright tests
echo "Running Playwright tests..."
mvn clean test -Dsurefire.failIfNoTests=false -DtestFailureIgnore=true    

# Verify test results exist
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

# Send Email
echo -e "Subject: $EMAIL_SUBJECT\nMIME-Version: 1.0\nContent-Type: text/html\n\n$EMAIL_BODY" | sendmail -v $EMAIL_RECIPIENTS
