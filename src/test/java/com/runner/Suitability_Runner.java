package com.runner;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.CreateApplication;
import com.actions.ExcelTestDataUtil;
import com.actions.Suitability_Action;
import com.base.AbstractRunner;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;
import com.utils.ExcelReader;

import io.qameta.allure.Feature;

public class Suitability_Runner extends AbstractRunner {
	
	@Test
	@Order(2)
	@Tag("positive")
	@Feature("Suitability Information Positive Flow")
	public void ownerInformation() {
		try {

			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:1");

			CreateApplication createApplication = new CreateApplication();
//			Method createMethod = CreateApplication.class.getMethod("createApplicationPopup", Page.class,Map.class);
			Method createMethod = CreateApplication.class.getMethod("name", Page.class, Map.class);

			Suitability_Action product = new Suitability_Action();
			Method additionalInformation = Suitability_Action.class.getMethod("additionalInformation", Page.class, Map.class);
			Method financialResources = Suitability_Action.class.getMethod("financialResources", Page.class, Map.class);
			Method financialProfile = Suitability_Action.class.getMethod("financialProfile", Page.class, Map.class);
			Method Replacements = Suitability_Action.class.getMethod("Replacements", Page.class, Map.class);
			Method ownerAgentStatements = Suitability_Action.class.getMethod("ownerAgentStatements", Page.class, Map.class);

			logger.info("Method taken from the createapplication and the method name is " + createMethod);
			
			Object[][] executionData = { { createApplication, createMethod, rowData },
//					{ product, additionalInformation, rowData },
					{ product, financialResources, rowData },
					{ product, financialProfile, rowData },
					{ product, Replacements, rowData },
					{ product, ownerAgentStatements, rowData}};
			executeTestAcrossBrowsers(executionData);
		} catch (NoSuchMethodException e) {
			logger.error("The specified method could not be found: " + e.getMessage(), e);
		} catch (Exception e) {
			ErrorHandler.handleError("Error during Test", e);
		}
	}
	
}
