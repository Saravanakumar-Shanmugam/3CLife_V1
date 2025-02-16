#!/bin/bash

# Set script to exit immediately if any command fails
set -e  

# Navigate to the project directory
cd "$(dirname "$0")"

# Run Playwright tests (ignore test failures)
echo "Running Playwright tests..."
mvn clean test -Dsurefire.failIfNoTests=false -DtestFailureIgnore=true    

# Verify test results exist
ALLURE_RESULTS_DIR="$WORKSPACE/allure-results"
if [ ! -d "$ALLURE_RESULTS_DIR" ] || [ -z "$(ls -A $ALLURE_RESULTS_DIR)" ]; then
    echo "Error: No Allure test results found in $ALLURE_RESULTS_DIR!"
    exit 1
fi

# Debugging: List files in allure-results to confirm it has test details
echo "Contents of allure-results directory:"
ls -lh "$ALLURE_RESULTS_DIR"

# Ensure Allure binary is available
if ! command -v allure &> /dev/null; then
    echo "Error: Allure command not found! Please install Allure or check your PATH."
    exit 1
fi

# Generate a self-contained Allure report
ALLURE_REPORT_DIR="$WORKSPACE/allure-report"
echo "Generating Allure report..."
allure generate --clean "$ALLURE_RESULTS_DIR" -o "$ALLURE_REPORT_DIR" || {
    echo "Error: Allure report generation failed!"
    exit 1
}

# Verify if report generation was successful
if [ ! -d "$ALLURE_REPORT_DIR" ] || [ -z "$(ls -A $ALLURE_REPORT_DIR)" ]; then
    echo "Error: Allure report directory is empty!"
    exit 1
fi

echo "Allure report successfully generated at: $ALLURE_REPORT_DIR"

# Zip the report (optional)
if command -v zip &> /dev/null; then
    echo "Zipping the Allure report for archiving..."
    zip -r "$WORKSPACE/allure-report.zip" "$ALLURE_REPORT_DIR"
else
    echo "Warning: zip command not found, skipping archive creation."
fi

echo "Script execution completed!"
