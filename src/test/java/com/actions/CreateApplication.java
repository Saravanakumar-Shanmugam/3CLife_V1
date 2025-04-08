package com.actions;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.BaseAction;
import com.config.ConfigReader;
import com.microsoft.playwright.Page;
import com.page.CreateApplicationPopup;
import com.utils.ErrorHandler;

public class CreateApplication {

	private static final Logger logger = LoggerFactory.getLogger(CreateApplication.class);

	public static void createApplicationPopup(Page page, Map<String, String> rowData) {
		try {
			if (page.url().equals(ConfigReader.getBaseURL())) {
				LoginPageActions.login(page);
			}
			logger.info("Starting the application creation process...");
			BaseAction.logStepAndClick(page, "Click 'Create new application' button",
					CreateApplicationPopup.createApplicationButton);
			BaseAction.validateElementState(page, CreateApplicationPopup.startApplicationButton, true, "enabled");
			logger.info("Select " + rowData.get("Application_State") + " for State");
			BaseAction.drSelection(page, CreateApplicationPopup.stateDropdown,rowData.get("Application_State"));
			BaseAction.logStepAndClick(page, "Click 'Start Application' button",
					CreateApplicationPopup.startApplicationButton);
			BaseAction.waitForNetworkIdle(page);
			BaseAction.verifyNavigationSuccess(page, "agent-detail-page", ConfigReader.getTimeout());
		} catch (Exception e) {
			ErrorHandler.handleError("Create Application action", e, page);
		}
	}

	public static void firstRowSelection(Page page, Map<String, String> rowData) {
		try {
			if (page.url().equals(ConfigReader.getBaseURL())) {
				LoginPageActions.login(page);
			}
			page.locator("(//div[@id='row-1']/div/div)[1]").click();
		} catch (Exception e) {
			ErrorHandler.handleError("Create Application action", e, page);
		}

	}
}
