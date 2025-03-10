package com.runner;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.CreateApplication;
import com.actions.ExcelTestDataUtil;
import com.actions.Owner_Actions;
import com.base.AbstractRunner;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;
import com.utils.ExcelReader;

import io.qameta.allure.Feature;

public class Owner_Runner extends AbstractRunner {
	
	@Test
	@Order(2)
	@Tag("positive")
	@Feature("owner Information Positive Flow")
	public void ownerInformation() {
		try {

			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:3");

			CreateApplication createApplication = new CreateApplication();
//			Method createMethod = CreateApplication.class.getMethod("createApplicationPopup", Page.class,Map.class);
			Method createMethod = CreateApplication.class.getMethod("name", Page.class, Map.class);

			Owner_Actions product = new Owner_Actions();
			Method productmethod = Owner_Actions.class.getMethod("ownerInformation", Page.class, Map.class);
		
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
	
	
//	@Test
	@Order(1)
	@Tag("Negative")
	@Feature("owner Information Required validation Flow")
	public void RequiredValidation() {
		try {

			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:1");
			Map<String, String> ValidationData = ExcelTestDataUtil.getValidationData().get("TestCase-No:1");
			Map<String, String> optionsData = ExcelTestDataUtil.getOptionsData().get("TestCase-No:1");


			CreateApplication createApplication = new CreateApplication();
//			Method createMethod = CreateApplication.class.getMethod("createApplicationPopup", Page.class,Map.class);
			Method createMethod = CreateApplication.class.getMethod("name", Page.class, Map.class);

			Owner_Actions product = new Owner_Actions();
			Method requiredValidation = Owner_Actions.class.getMethod("requiredValidation", Page.class, Map.class);
			Method optionValidations = Owner_Actions.class.getMethod("optionValidations", Page.class, Map.class);

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
	
}
