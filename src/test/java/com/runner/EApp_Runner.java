package com.runner;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.App_Processor;
import com.base.AbstractRunner;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;
import com.utils.ExcelReader;

import io.qameta.allure.Feature;

public class EApp_Runner extends AbstractRunner {

	@Test
	@Order(1)
	@Tag("positive")
	@Feature("E-Application Positive Flow")
	public void ownerInformation() {
		try {
			Map<String, Map<String, String>> data = ExcelReader.getData(AppConstants.EXCEL_FILE_PATH,
					AppConstants.SHEET_NAME);
			Map<String, String> rowData = data.get("TestCase-No:1");
			App_Processor product = new App_Processor();
			Method productmethod = App_Processor.class.getMethod("possitiveProcess", Page.class, Map.class);
			Object[][] executionData = { { product, productmethod, rowData } };
			executeTestAcrossBrowsers(executionData);
		} catch (
		NoSuchMethodException e) {
			logger.error("The specified method could not be found: " + e.getMessage(), e);
		} catch (Exception e) {
			ErrorHandler.handleError("Error during Test", e);
		}
	}

}
