package com.runner;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.CreateApplication;
import com.actions.ExcelTestDataUtil;
import com.actions.Funding_Action;
import com.base.AbstractRunner;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;

import io.qameta.allure.Feature;

public class Funding_Runner extends AbstractRunner {

	
	@Test
	@Order(1)
	@Tag("positive")
	@Feature("Beneficiaries Information Positive Flow")
	public void ownerInformation() {
		try { 
			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:3");

			CreateApplication createApplication = new CreateApplication();
//			Method createMethod = CreateApplication.class.getMethod("createApplicationPopup", Page.class,Map.class);
			Method createMethod = CreateApplication.class.getMethod("firstRowSelection", Page.class, Map.class);

			Funding_Action product = new Funding_Action();
			Method fundingFlow = Funding_Action.class.getMethod("fundingFlow", Page.class, Map.class);
		
			logger.info("Method taken from the createapplication and the method name is " + createMethod);
			
			Object[][] executionData = { { createApplication, createMethod, rowData },
					{ product, fundingFlow, rowData } };
			executeTestAcrossBrowsers(executionData);
		} catch (NoSuchMethodException e) {
			logger.error("The specified method could not be found: " + e.getMessage(), e);
		} catch (Exception e) {
			ErrorHandler.handleError("Error during Test", e);
		}
	}
	
	
}
