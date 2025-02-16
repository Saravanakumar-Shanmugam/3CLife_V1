package com.runner;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.CreateApplication;
import com.base.AbstractRunner;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;
import com.utils.ExcelReader;

import io.qameta.allure.Feature;

public class CreateApplicationRunner extends AbstractRunner {

	@Test
	@Tag("positive")
	@Feature("Testing create application popup Positive Flow")
	public void createApplicationPopup() {
		try {
			CreateApplication createApplication = new CreateApplication();
			Method createMethod = CreateApplication.class.getMethod("createApplicationPopup", Page.class,Map.class);
			logger.info("Method taken from the createapplication and the method name is "+createMethod);
	       
			Map<String, Map<String, String>> data = ExcelReader.getData(AppConstants.EXCEL_FILE_PATH,
					AppConstants.SHEET_NAME);
			Map<String, String> rowData = data.get("TestCase-No:1");
			
			  Object[][] executionData = {
			            { createApplication, createMethod, rowData }
			        };
				executeTestAcrossBrowsers(executionData);
		 } catch (NoSuchMethodException e) {
		        logger.error("The specified method could not be found: " + e.getMessage(), e);
		    } catch (Exception e) {
		        ErrorHandler.handleError("Error during Test", e);
		    }
	}
}
