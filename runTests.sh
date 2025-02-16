#!/bin/bash

# Set script to exit immediately if any command fails
set -e  

# Navigate to the project directory
cd "$(dirname "$0")"

# Run Playwright tests (ignore test failures)
echo "Running Playwright tests..."
mvn clean test -Dsurefire.failIfNoTests=false -DtestFailureIgnore=true    

# Verify test results exist
if [ ! -d "allure-results" ] || [ -z "$(ls -A allure-results)" ]; then
    echo "Error: No Allure test results found in allure-results!"
    exit 1
fi

# Debugging: List files in allure-results to confirm it has test details
echo "Contents of allure-results directory:"
ls -lh allure-results

# Ensure Allure binary is available
if ! command -v allure &> /dev/null; then
    echo "Error: Allure command not found! Please install Allure or check your PATH."
    exit 1
fi

# Generate a self-contained Allure report
echo "Generating Allure report..."
allure generate --clean allure-results -o allure-report || {
    echo "Error: Allure report generation failed!"
    exit 1
}

# Verify if report generation was successful
if [ ! -d "allure-report" ] || [ -z "$(ls -A allure-report)" ]; then
    echo "Error: Allure report directory is empty!"
    exit 1
fi

echo "Allure report successfully generated at: allure-report/"

# Open the report using a background process so the script doesn't hang
echo "Opening Allure report..."
nohup allure open allure-report > /dev/null 2>&1 &

# Wait for a moment to ensure the server starts
sleep 3

echo "Allure report should now be accessible."

# Attempt to zip the report (skip if zip is missing)
if command -v zip &> /dev/null; then
    echo "Zipping the Allure report for sharing..."
    zip -r allure-report.zip allure-report
else
    echo "Warning: zip command not found, skipping archive creation."
fi

# Compile and run EmailReportSender
echo "Compiling EmailReportSender..."
mvn clean compile  

echo "Sending report via EmailReportSender..."
java -cp "target/classes:target/dependency/*" com.utils.EmailReportSender allure-report/index.html

echo "Script execution completed!"
