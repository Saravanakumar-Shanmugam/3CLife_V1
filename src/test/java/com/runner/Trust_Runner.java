package com.runner;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.CreateApplication;
import com.actions.ExcelTestDataUtil;
import com.actions.Trust_Actions;
import com.base.AbstractRunner;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;

import io.qameta.allure.Feature;

public class Trust_Runner extends AbstractRunner {
	
	@Test
	@Order(1)
	@Tag("positive")
	@Feature("Trust Information Positive Flow")
	public void ownerInformation() {
		try {

			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:4");

			CreateApplication createApplication = new CreateApplication();
//			Method createMethod = CreateApplication.class.getMethod("createApplicationPopup", Page.class,Map.class);
			Method createMethod = CreateApplication.class.getMethod("name", Page.class, Map.class);

			Trust_Actions product = new Trust_Actions();
			Method productmethod = Trust_Actions.class.getMethod("trustFlow", Page.class, Map.class);
		
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
