package com.runner;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.Additional_Information_Action;
import com.actions.CreateApplication;
import com.actions.ExcelTestDataUtil;
import com.base.AbstractRunner;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;

import io.qameta.allure.Feature;

public class Additional_Information_Runner extends AbstractRunner{

	@Test
	@Order(2)
	@Tag("positive")
	@Feature("Additional Information Positive Flow")
	public void ownerInformation() {
		try {

			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:3");

			CreateApplication createApplication = new CreateApplication();
//			Method createMethod = CreateApplication.class.getMethod("createApplicationPopup", Page.class,Map.class);
			Method createMethod = CreateApplication.class.getMethod("firstRowSelection", Page.class, Map.class);

			Additional_Information_Action product = new Additional_Information_Action();
			Method productmethod = Additional_Information_Action.class.getMethod("AdditionalInformation", Page.class, Map.class);
		
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
