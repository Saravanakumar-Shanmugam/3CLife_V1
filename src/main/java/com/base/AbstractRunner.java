package com.base;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.config.PlaywrightConfig;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;

import io.qameta.allure.Step;


public abstract class AbstractRunner {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractRunner.class);

    @AfterAll
    @Step("Teardown test environment")
    public static void afterAllTests() {
        try {
            logger.info("Teardown test environment");
            PlaywrightConfig.tearDown();
        } catch (Exception e) {
            ErrorHandler.handleError("Error during teardown", e, null);
        }
    }

    public static void executeTestAcrossBrowsers(Object[][] executionData) {
        // Retrieve supported browsers from the configuration
        List<String> supportedBrowsers = PlaywrightConfig.getBrowsers();

        for (String browser : supportedBrowsers) {
            Page page = null;
            try {
                logger.info("Starting tests on browser: {}", browser);
                page = initializeBrowser(browser);
                executeTests(page, executionData);
                logger.info("Tests completed successfully on browser: {}", browser);
            } catch (Exception e) {
                logger.error("Error executing tests on browser: {}", browser, e);
                ErrorHandler.handleError("Error occurred while testing on browser: " + browser, e, page);
            } finally {
                // Always clean up browser and resources
                if (page != null) {
                    try {
                        page.context().close();
                        page.close();
                    } catch (Exception cleanupException) {
                        logger.error("Error during browser cleanup: {}", cleanupException.getMessage(), cleanupException);
                    }
                }
                PlaywrightConfig.tearDown();
            }
        }
    }

    private static Page initializeBrowser(String browser) {
        int retries = 3;
        for (int attempt = 1; attempt <= retries; attempt++) {
            try {
                logger.info("Attempting to initialize browser: {} (Attempt {})", browser, attempt);
                return PlaywrightConfig.setupBrowser(browser);
            } catch (Exception e) {
                logger.error("Failed to initialize browser: {} on attempt {}", browser, attempt, e);
                if (attempt == retries) {
                    throw new RuntimeException("Failed to initialize browser after " + retries + " attempts", e);
                }
            }
        }
        return null; // This should never be reached
    }

    private static void executeTests(Page page, Object[][] executionData) throws Exception {
        for (Object[] dataRow : executionData) {
            validateExecutionData(dataRow);
            invokeTestMethod(page, dataRow);
        }
    }

    private static void validateExecutionData(Object[] dataRow) {
        if (dataRow.length != 3) {
            throw new IllegalArgumentException(
                    "Each execution set must have exactly 3 elements: object, method, and data.");
        }
    }


    private static void invokeTestMethod(Page page, Object[] dataRow) throws Exception {
        Object instance = dataRow[0];
        Method method = (Method) dataRow[1];
        Map<String, String> rowData = (Map<String, String>) dataRow[2];

        logger.info("Executing method: {} on instance: {}", method.getName(), instance.getClass().getName());
        try {
            if (rowData != null) {
                method.invoke(instance, page, rowData);
            } else {
                method.invoke(instance, page);
            }
        } catch (Exception e) {
            logger.error("Error invoking method: {}", method.getName(), e);
            throw e; // Ensure exceptions are propagated for proper handling
        }

        BaseAction.waitForNetworkIdle(page);
    }
}
