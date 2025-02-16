package com.runner;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.actions.LoginPageActions;
import com.base.AbstractRunner;
import com.microsoft.playwright.Page;
import com.utils.ErrorHandler;

import io.qameta.allure.Feature;

public class LoginPageRunner extends AbstractRunner {

	@Test
	@Tag("positive")
	@Feature("Login Page Positive Test")
	public void testLoginPagePositive() {
		try {
			LoginPageActions loginPageActions = new LoginPageActions();
			Method loginMethod = LoginPageActions.class.getMethod("login", Page.class);
			logger.info("Method taken from the LoginPageAcation and the method name is " + loginMethod);
			
	        Object[][] executionData = {
		            { loginPageActions, loginMethod, null }
		        };
			executeTestAcrossBrowsers(executionData);
		} catch (Exception e) {
			ErrorHandler.handleError("Error during Positive Login Test", e);
		}
	}

//	@Test
	@Tag("negative")
	@Feature("Login Page Negative Test")
	public void testLoginPageNegative() {
		try {
			LoginPageActions loginPageActions = new LoginPageActions();
			Method loginMethod = LoginPageActions.class.getMethod("login", Page.class);
			  Object[][] executionData = {
			            { loginPageActions, loginMethod, null }
			        };
				executeTestAcrossBrowsers(executionData);
				} catch (Exception e) {
			ErrorHandler.handleError("Error during Positive Login Test", e);
		}
	}
}
