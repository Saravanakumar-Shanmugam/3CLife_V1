#!/bin/bash

set -e  # Exit on error
set -x  # Debug mode (optional)

cd "$(dirname "$0")"

# Run Playwright tests
echo "🔹 Running Playwright tests..."
mvn clean test -Dtest=com.runner.TestSuiteRunner -Dsurefire.failIfNoTests=false -DtestFailureIgnore=true

# Verify if test results exist
if [ ! -d "target/allure-results" ] || [ "$(find target/allure-results -type f | wc -l)" -eq 0 ]; then
    echo "❌ Error: No Allure test results found!"
    exit 1
fi

# Check if Allure CLI is installed
if ! command -v allure &> /dev/null; then
    echo "❌ Error: Allure CLI is not installed or not in PATH. Please install it."
    exit 1
fi

# Generate Allure report
echo "🔹 Generating Allure report..."
allure generate --clean target/allure-results -o allure-report

# Get GitHub Actions Job URL
GITHUB_RUN_URL="https://github.com/${GITHUB_REPOSITORY}/actions/runs/${GITHUB_RUN_ID}"

# Ensure application.properties exists before modifying
if [ ! -f "src/main/resources/application.properties" ]; then
    echo "❌ Error: application.properties file not found!"
    exit 1
fi

# Replace {REPORT_URL} placeholder in application.properties (cross-platform)
if [[ "$OSTYPE" == "darwin"* ]]; then
    sed -i '' "s|{REPORT_URL}|$GITHUB_RUN_URL|g" src/main/resources/application.properties
else
    sed -i "s|{REPORT_URL}|$GITHUB_RUN_URL|g" src/main/resources/application.properties
fi

# Send email using EmailSender class from com.utils package
echo "📧 Sending email with report link: $GITHUB_RUN_URL"
mvn exec:java -Dexec.mainClass="com.utils.EmailSender" -Dexec.classpathScope=test -Dexec.args="$GITHUB_RUN_URL"

echo "✅ Test execution and reporting completed!"
