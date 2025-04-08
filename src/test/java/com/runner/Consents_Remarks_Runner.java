package com.runner;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.Consents_Remarks_Actions;
import com.actions.CreateApplication;
import com.actions.ExcelTestDataUtil;
import com.base.AbstractRunner;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;

import io.qameta.allure.Feature;

public class Consents_Remarks_Runner extends AbstractRunner {

	@Test
	@Order(1)
	@Tag("positive")
	@Feature("Consents & Remarks Information Positive Flow")
	public void ownerInformation() {
		try {
			Map<String, String> rowData = ExcelTestDataUtil.getData().get("TestCase-No:2");

			CreateApplication createApplication = new CreateApplication();
//			Method createMethod = CreateApplication.class.getMethod("createApplicationPopup", Page.class,Map.class);
			Method createMethod = CreateApplication.class.getMethod("firstRowSelection", Page.class, Map.class);

			Consents_Remarks_Actions product = new Consents_Remarks_Actions();
			Method Consents = Consents_Remarks_Actions.class.getMethod("Consents", Page.class, Map.class);
			Method remarks = Consents_Remarks_Actions.class.getMethod("remarks", Page.class, Map.class);

			logger.info("Method taken from the createapplication and the method name is " + createMethod);

			Object[][] executionData = { { createApplication, createMethod, rowData }, { product, Consents, rowData },
					{ product, remarks, rowData } };
			executeTestAcrossBrowsers(executionData);
		} catch (NoSuchMethodException e) {
			logger.error("The specified method could not be found: " + e.getMessage(), e);
		} catch (Exception e) {
			ErrorHandler.handleError("Error during Test", e);
		}
	}
}
