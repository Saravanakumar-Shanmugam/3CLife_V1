package com.runner;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.App_Processor;
import com.actions.ExcelTestDataUtil;
import com.base.AbstractRunner;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;

import io.qameta.allure.Feature;

public class EApp_Runner extends AbstractRunner {

	@Test
	@Order(1)
	@Tag("positive")
	@Feature("E-Application Positive Flow")
	public void ownerInformation() {
		try {
	        List<String> columnNames = ExcelTestDataUtil.getColumnNames(ExcelTestDataUtil.getData());
	        for (String column : columnNames) {
//			Map<String, String> rowData = ExcelTestDataUtil.getData().get(column);
			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:3");
			App_Processor product = new App_Processor();
			Method productmethod = App_Processor.class.getMethod("possitiveProcess", Page.class, Map.class);
			Object[][] executionData = { { product, productmethod, rowData } };
			executeTestAcrossBrowsers(executionData);
	        }
		} catch (
		NoSuchMethodException e) {
			logger.error("The specified method could not be found: " + e.getMessage(), e);
		} catch (Exception e) {
			ErrorHandler.handleError("Error during Test", e);
		}
	}

}
