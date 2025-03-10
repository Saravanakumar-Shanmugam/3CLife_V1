package com.runner;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.CreateApplication;
import com.actions.Documents_Signature_Actions;
import com.actions.ExcelTestDataUtil;
import com.base.AbstractRunner;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;

import io.qameta.allure.Feature;

public class Documents_Signature_Runner extends AbstractRunner {
	
	@Test
	@Order(1)
	@Tag("positive")
	@Feature("Documents & Signature Information Positive Flow")
	public void ownerInformation() {
		try {
			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:2");

			CreateApplication createApplication = new CreateApplication();
			Method createMethod = CreateApplication.class.getMethod("name", Page.class, Map.class);

			Documents_Signature_Actions product = new Documents_Signature_Actions();
			Method Consents = Documents_Signature_Actions.class.getMethod("uploadDocument", Page.class, Map.class);
			Method signature = Documents_Signature_Actions.class.getMethod("signature", Page.class, Map.class);

			logger.info("Method taken from the createapplication and the method name is " + createMethod);

			Object[][] executionData = { { createApplication, createMethod, rowData },
					{ product, Consents, rowData },
					{ product, signature, rowData }};
			executeTestAcrossBrowsers(executionData);
		} catch (NoSuchMethodException e) {
			logger.error("The specified method could not be found: " + e.getMessage(), e);
		} catch (Exception e) {
			ErrorHandler.handleError("Error during Test", e);
		}
	}
	
}
