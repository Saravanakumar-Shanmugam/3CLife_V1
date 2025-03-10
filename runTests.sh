#!/bin/bash

set -e  # Exit on error

cd "$(dirname "$0")"

# Run Playwright tests and ensure Allure results are saved
echo "Running Playwright tests..."
mvn clean test -Dtest=com.runner.TestSuiteRunner -Dsurefire.failIfNoTests=false -DtestFailureIgnore=true \
    -Dallure.results.directory=allure-results

# Verify if test results exist
if [ ! -d "allure-results" ] || [ "$(find allure-results -type f | wc -l)" -eq 0 ]; then
    echo "❌ Error: No Allure test results found!"
    exit 1
fi

# Generate Allure report
echo "Generating Allure report..."
allure generate --clean allure-results -o allure-report || { echo "❌ Allure report generation failed!"; exit 1; }
