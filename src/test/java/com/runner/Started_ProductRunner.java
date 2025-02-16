package com.runner;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.actions.CreateApplication;
import com.actions.ExcelTestDataUtil;
import com.actions.Started_Product;
import com.base.AbstractRunner;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;

import io.qameta.allure.Feature;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Started_ProductRunner extends AbstractRunner {

	@Test
	@Order(1)
	@Tag("Negative")
	@Feature("Let's Get Started / Product section Negative Flow")
	public void validation() {
		try {
			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:1");
			Map<String, String> ValidationData = ExcelTestDataUtil.getValidationData().get("TestCase-No:1");
			Map<String, String> optionsData = ExcelTestDataUtil.getOptionsData().get("TestCase-No:1");

			CreateApplication createApplication = new CreateApplication();
			Method createMethod = CreateApplication.class.getMethod("createApplicationPopup", Page.class,Map.class);
			Started_Product product = new Started_Product();
			Method requiredValidation = Started_Product.class.getMethod("requiredValidation", Page.class, Map.class);
			Method optionValidations = Started_Product.class.getMethod("optionValidations", Page.class, Map.class);
			
			logger.info("Method taken from the createapplication and the method name is " + createMethod);
			Object[][] executionData = { { createApplication, createMethod, rowData },
					{ product, requiredValidation, ValidationData },
					{ product, optionValidations, optionsData }};

			executeTestAcrossBrowsers(executionData);

		} catch (NoSuchMethodException e) {
			logger.error("The specified method could not be found: " + e.getMessage(), e);
		} catch (Exception e) {
			ErrorHandler.handleError("Error during Test", e);
		}
	}

	@Test
	@Order(2)
	@Tag("positive")
	@Feature("Let's Get Started / Product section Positive Flow")
	public void createApplicationPopup() {
		try {
			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:1");

			CreateApplication createApplication = new CreateApplication();
			Method createMethod = CreateApplication.class.getMethod("name", Page.class, Map.class);

			Started_Product product = new Started_Product();
			Method productmethod = Started_Product.class.getMethod("StartedProduct", Page.class, Map.class);
			logger.info("Method taken from the createapplication and the method name is " + createMethod);
			Object[][] executionData = { { createApplication, createMethod, rowData },
					{ product, productmethod, rowData } };
			executeTestAcrossBrowsers(executionData);
		} catch (NoSuchMethodException e) {
			logger.error("The specified method could not be found: " + e.getMessage(), e);
		} catch (Exception e) {
			ErrorHandler.handleError("Error during Test", e);
		}
	}
}
