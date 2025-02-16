package com.actions;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.BaseAction;
import com.config.ConfigReader;
import com.constants.AppConstants;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.page.LoginPage;
import com.utils.AllureUtils;
import com.utils.ErrorHandler;

public class LoginPageActions {

	public static final Logger logger = LoggerFactory.getLogger(LoginPageActions.class);

	public static void login(Page page) {
		try {
			if (page.url().equals(ConfigReader.getBaseURL())) {
				logger.info("Processing the login functionality...");
				performLoginSteps(page);
				BaseAction.verifyNavigationSuccess(page, AppConstants.LOGIN_BYPASS_URL_PATTERN,30000);
				saveState(page);
				return;
			} else if (page.url().contains(AppConstants.LOGIN_BYPASS_URL_PATTERN)) {
				logger.info("Bypassing the login page by using the auth state.");
				AllureUtils.logStep("Bypassing the login page by using the auth state.");
			} else {
				logger.error("Page redirection issue: " + page.url());
			}
		} catch (Exception e) {
			ErrorHandler.handleError("Login action", e, page);
		}
	}

	private static void performLoginSteps(Page page) {
		AllureUtils.logStep("Filling in Email");
		BaseAction.fillInputField(page, LoginPage.emailInput, ConfigReader.getUsername());
		AllureUtils.logStep("Filling in Password");
		BaseAction.fillInputField(page, LoginPage.passwordInput, ConfigReader.getPassword());
		AllureUtils.logStep("Clicking login button");
		BaseAction.clickElement(page, LoginPage.loginInput);
	}

	private static void saveState(Page page) {
		logger.info("Saving and storing the state to a file.");
		BrowserContext context = page.context();
		context.storageState(
				new BrowserContext.StorageStateOptions().setPath(Paths.get(AppConstants.AUTH_STATE_FILE_PATH)));
	}
}
