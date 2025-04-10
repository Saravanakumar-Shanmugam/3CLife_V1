package com.config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.BaseAction;
import com.constants.AppConstants;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.utils.AllureUtils;

public class PlaywrightConfig {

    private static final Logger logger = LoggerFactory.getLogger(PlaywrightConfig.class);
    private static final ThreadLocal<Playwright> playwright = ThreadLocal.withInitial(Playwright::create);
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static BrowserContext context;
    private static Page page;

	private static boolean authStateExists = false;

    public static BrowserContext getContext() {
        return context;
    }

    public static Playwright getPlaywright() {
        return playwright.get();
    }

    public static Page setupBrowser(String browserType) {
        logger.info("Launching browser: " + browserType);
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(ConfigReader.isHeadlessMode())
                .setArgs(List.of("--disable-dev-shm-usage", "--no-sandbox"));

        Browser browserInstance = launchBrowser(playwright.get(), browserType, options);

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        setupContextOptions(contextOptions);

        context = browserInstance.newContext(contextOptions);
        startTracingIfDebugMode();

        page = context.newPage();
        navigateToInitialPage();

        AllureUtils.attachEnvironmentInfo();
        AllureUtils.addExecutorsInfo();
        browser.set(browserInstance);
        return page;
    }

    private static Browser launchBrowser(Playwright activePlaywright, String browserType, BrowserType.LaunchOptions options) {
        switch (browserType.toLowerCase()) {
            case AppConstants.BROWSER_CHROMIUM:
            case AppConstants.BROWSER_CHROME:
                return activePlaywright.chromium().launch(options);
            case AppConstants.BROWSER_FIREFOX:
                return activePlaywright.firefox().launch(options);
            case AppConstants.BROWSER_WEBKIT:
                return activePlaywright.webkit().launch(options);
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
    }

    private static void setupContextOptions(Browser.NewContextOptions contextOptions) {
        if (ConfigReader.isDebugMode()) {
            contextOptions.setRecordVideoDir(Paths.get(AppConstants.VIDEO_DIR_KEY))
                    .setRecordVideoSize(1280, 720);
        }

        if (Files.exists(Paths.get(AppConstants.AUTH_STATE_FILE_PATH))) {
            try {
                contextOptions.setStorageStatePath(Paths.get(AppConstants.AUTH_STATE_FILE_PATH));
                authStateExists = true;
            } catch (Exception e) {
                logger.error("Failed to load auth state file: " + AppConstants.AUTH_STATE_FILE_PATH, e);
                authStateExists = false;
            }
        }
    }

    private static void startTracingIfDebugMode() {
//        if (ConfigReader.isDebugMode()) {
//            context.tracing().start(new Tracing.StartOptions()
//                    .setScreenshots(true)
//                    .setSnapshots(true)
//                    .setSources(true));
//        }
    }

    private static void navigateToInitialPage() {
        String baseUrl = ConfigReader.getBaseURL().trim();
        if (authStateExists) {
            BaseAction.navigate(page, baseUrl + AppConstants.LOGIN_BYPASS_URL_PATTERN);
        } else {
            BaseAction.navigate(page, baseUrl);
            logger.info("Auth state file does not exist, navigating to login page.");
        }
    }

    public static List<String> getBrowsers() {
        return ConfigReader.getBrowsers();
    }

    public static void tearDown() {
        closeResource(browser.get(), "browser");
        closeResource(playwright.get(), "Playwright");

        // Remove ThreadLocal references
        browser.remove();
        playwright.remove();
    }

    private static void closeResource(AutoCloseable resource, String resourceName) {
        try {
            if (resource != null) {
                logger.info("Closing " + resourceName + " and removing the reference...");
                resource.close();
            } else {
                logger.warn("No " + resourceName + " instance to close.");
            }
        } catch (Exception e) {
            logger.error("Error during " + resourceName + " cleanup: " + e.getMessage(), e);
        }
    }
}
