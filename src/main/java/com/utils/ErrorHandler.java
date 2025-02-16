package com.utils;
import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;

import com.microsoft.playwright.Page;

import io.qameta.allure.Allure;

public class ErrorHandler {

	static Page page;

	public static void handleError(String message, Exception exception, Page page) {
	    try {
	        // Unwrap the cause if it's an InvocationTargetException
	        Throwable cause = (exception instanceof InvocationTargetException) ? exception.getCause() : exception;
	        ErrorHandler.page = page;

	        // Log the exception
	        System.err.println(message);
	        cause.printStackTrace();

	        // Log in Allure
	        Allure.step(message + " - " + (cause != null ? cause.getMessage() : "No additional details"));
	        Allure.addAttachment("Error Message", cause != null ? cause.getMessage() : "No message provided");
	        Allure.addAttachment("Error Stacktrace", cause != null ? cause.toString() : "No stack trace available");

	        if (page != null) {
	            // Attach screenshot to Allure
	            AllureUtils.attachScreenshot(page);

	            // Attach video to Allure if available
	            if (page.video() != null) {
	                AllureUtils.attachVideo(page);
	            }
	        }

	        // Mark the test as failed and re-throw an exception to propagate the failure
	        Assert.fail(message);
	        throw new RuntimeException(message, exception);

	    } catch (Exception e) {
	        System.err.println("Error while handling exception: " + e.getMessage());
	        throw new RuntimeException("Failed to handle exception properly.", e);
	    }
	}


	// Overloaded method without the Page object
	public static void handleError(String message, Exception exception) {
		handleError(message, exception, page); // Pass null for page if it's not available
	}
}
