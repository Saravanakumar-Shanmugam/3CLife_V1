#!/bin/bash

set -e  # Exit on error

cd "$(dirname "$0")"

# Run Playwright tests
echo "Running Playwright tests..."
mvn clean test -Dsurefire.failIfNoTests=false -DtestFailureIgnore=true

# Verify if test results exist
if [ ! -d "allure-results" ] || [ -z "$(ls -A allure-results)" ]; then
    echo "Error: No Allure test results found!"
    exit 1
fi

# Generate Allure report
echo "Generating Allure report..."
allure generate --clean allure-results -o allure-report

# Set Jenkins Report URL
JENKINS_URL="http://your-jenkins-server/job/sample/allure-report"

# Replace {REPORT_URL} placeholder in email body
sed -i "s|{REPORT_URL}|$JENKINS_URL|g" src/main/resources/application.properties

# Send email using EmailSender class from com.utils package
echo "Sending email..."
mvn exec:java -Dexec.mainClass="com.utils.EmailSender"

echo "✅ Test execution and reporting completed!"
