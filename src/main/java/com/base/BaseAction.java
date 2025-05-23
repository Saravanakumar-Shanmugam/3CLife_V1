package com.base;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.config.ConfigReader;
import com.constants.AppConstants;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.selectors.LocatorFactory;
import com.selectors.Selector;
import com.selectors.SelectorType;
import com.utils.AllureUtils;

public class BaseAction {

	public static final Logger logger = LoggerFactory.getLogger(BaseAction.class);
	static PrintStream originalErr = System.err;
	public static Selector options = new Selector(SelectorType.XPATH, "//div[@role='option']");
	public static Selector selectedDate = new Selector(SelectorType.XPATH,
			"//div[@role='listbox']//div[@aria-selected='true']");

	// Retrieves the currently active test method
	public static String getActiveTestMethod() {
		return Arrays.stream(Thread.currentThread().getStackTrace())
				.filter(element -> element.getClassName().contains("runner")).findFirst()
				.map(StackTraceElement::getMethodName).orElse("Unknown Test Method");
	}

	// Navigates to a URL and waits for the page to load
	public static void navigate(Page page, String url) {
		page.navigate(url);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		logger.info("Current page URL is: {}", page.url());
	}

	// Selects a dropdown value by text (specific implementation)
	public static void drSelection(Page page, Selector selector, String value) {
		if (!value.isBlank() || !value.isEmpty()) {
			clickElement(page, selector);
			selectByValue(page, options, value);
		}
	}

	public static void drSelectionByIndex(Page page, Selector selector, String value, int index) {
		if (!value.isBlank() || !value.isEmpty()) {
			clickElementByIndex(page, selector, index);
			selectByValue(page, options, value);
		}
	}
	
	public static void drSelectionContain(Page page, String value) {
		if (!value.isBlank() || !value.isEmpty()) {
			selectByValueContain(page, options, value);
		}
	}

	// Generic method to select dropdown value by text
	public static void selectByValue(Page page, Selector selector, String value) {
		Locator locator = getLocator(page, selector);
		waitForElement(page, selector);
		for (int i = 0; i < locator.count(); i++) {
			if (locator.nth(i).innerText().trim().equalsIgnoreCase(value)) {
				locator.nth(i).click();
				return;
			}
		}
		logger.error("{} is not found in the Options.", value);
	}

	public static void multiSelectByValue(Page page, Selector selector, String value) {
		Locator locator = getLocator(page, selector);
		waitForElement(page, selector);
		List<String> options = split(value);
		List<String> notFoundOptions = new ArrayList<>();

		for (String option : options) {
			boolean isOptionFound = false; // Reset for each option

			for (int j = 0; j < locator.count(); j++) {
				if (locator.nth(j).innerText().trim().equalsIgnoreCase(option)) {
					locator.nth(j).click();
					isOptionFound = true;
					break; // Stop searching once found
				}
			}

			if (!isOptionFound) {
				notFoundOptions.add(option);
			}
		}

		if (!notFoundOptions.isEmpty()) {
			logger.error("The following options were not found: {}", String.join(", ", notFoundOptions));
		}
	}

	// Generic method to select dropdown value by text Contain
	public static void selectByValueContain(Page page, Selector selector, String value) {
		Locator locator = getLocator(page, selector);
		waitForElement(page, selector);
		for (int i = 0; i < locator.count(); i++) {
			if (locator.nth(i).innerText().trim().contains(value.trim())) {
				locator.nth(i).click();
				return;
			}
		}
		logger.error("{} is not found in the Options.", value);
	}

	// Date picker implementation
	public static void datePicker(Page page, Selector selector, String date) {
		typeInputField(page, selector, date);
		clickElement(page, selectedDate);
	}

	// Utility method to fetch a Locator
	private static Locator getLocator(Page page, Selector selector) {
		return LocatorFactory.getLocator(page, selector);

	}

	// Utility method to fetch a Locator based on indes
	private static Locator getLocator(Page page, Selector selector, int index) {
		return LocatorFactory.getLocator(page, selector).nth(index);
	}

	private static boolean isNullOrBlank(String value) {
		return value == null || value.trim().isEmpty();
	}

	// Fills an input field
	public static void fillInputField(Page page, Selector selector, String value) {
		if (!isNullOrBlank(value)) {
			Locator locator = getLocator(page, selector);
			scrollToView(page, locator);
			if (locator.isVisible()) {
				locator.fill(value);
			} else {
				logger.warn("Field not visible: {}", selector.getValue());
			}

		}
	}

	// Type an input field
	public static void typeInputField(Page page, Selector selector, String value) {
		Locator locator = getLocator(page, selector);
		scrollToView(page, locator);
		if (locator.isVisible()) {
			locator.pressSequentially(value);
		} else {
			logger.warn("Field not visible: {}", selector.getValue());
		}
	}

	// Fills an input field
	public static void fillInputFieldByIndex(Page page, Selector selector, String value, int index) {
		Locator locator = getLocator(page, selector, index);
		scrollToView(page, locator);
		if (locator.isVisible()) {
			locator.click();
			locator.fill(value);
		} else {
			logger.warn("Field not visible: {}", selector.getValue());
		}
	}
	
	public static void selectByValueByIndex(Page page, Selector selector, String value,int index) {
		Locator locator = getLocator(page, selector, index);
		locator.waitFor();
		waitForElement(locator);
		locator.selectOption(value);
//		for (int i = 0; i < locator.count(); i++) {
//			if (locator.nth(i).textContent().trim().equalsIgnoreCase(value)) {
//				
//				locator.nth(i).click();
//				
//				return;
//			}
//		}
//		logger.error("{} is not found in the Options.", value);
	}

	public static void waitForElement(Locator locator) {
	    locator.waitFor(new Locator.WaitForOptions()
	        .setState(WaitForSelectorState.VISIBLE)
	        .setTimeout(ConfigReader.getTimeout()));
	}

	
	// Fills an input field
	public static void tYpeInputFieldByIndex(Page page, Selector selector, String value, int index) {
		Locator locator = getLocator(page, selector, index);
		scrollToView(page, locator);
		if (locator.isVisible()) {
			locator.pressSequentially(value);
		} else {
			logger.warn("Field not visible: {}", selector.getValue());
		}
	}

	// Clicks on an element and waits for it to be ready
	public static void clickElement(Page page, Selector selector) {
		try {
			Locator locator = getLocator(page, selector);
			scrollToView(page, locator);
			if (locator.isVisible() && locator.isEnabled()) {
				locator.click();
				waitForNetworkIdle(page);
			} else {
				logger.warn("Element not clickable: {}", selector.getValue());
			}
		} catch (Exception e) {
			logger.error("Failed to click the element: {}", selector.getValue(), e);
			throw e; // Rethrow the exception for proper error handling
		}
	}

	public static void clickElementByIndex(Page page, Selector selector, int index) {
		try {
			Locator locator = getLocator(page, selector, index);
			scrollToView(page, locator);
			if (locator.isVisible() && locator.isEnabled()) {
				locator.click();
				waitForNetworkIdle(page);
			} else {
				logger.warn("Element not clickable: {}", selector.getValue());
			}
		} catch (Exception e) {
			logger.error("Failed to click the element: {}", selector.getValue(), e);
			throw e; // Rethrow the exception for proper error handling
		}
	}

	// Waits for an element to be present in the DOM and ready for interaction
	public static void waitForElement(Page page, Selector selector) {
		try {
			page.waitForSelector(selector.getValue());
			waitForNetworkIdle(page);
		} catch (Exception e) {
			logger.error("Element not found within the timeout period: {}", selector.getValue(), e);
			throw e;
		}
	}

	public static void scrollToView(Page page, Locator locator) {
		ElementHandle elementHandle = locator.elementHandle();
		page.evaluate("el => el.scrollIntoView({ behavior: 'smooth', block: 'center' })", elementHandle);
		page.waitForTimeout(1000);
	}

	public static void waitForNetworkIdle(Page page) {
		try {
			page.waitForLoadState(LoadState.NETWORKIDLE);
			page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		} catch (Exception e) {
			logger.error("Failed to wait for NETWORKIDLE state.", e);
			throw e;
		}
	}

	// Checks if an element is visible
	public static boolean isElementVisible(Page page, Selector selector) {
		return getLocator(page, selector).isVisible();
	}

	public static void clickElementByIndex1(Page page, Selector selector, int index) {
		try {
			Locator locator = getLocator(page, selector, index);
			scrollToView(page, locator);
			if (locator.isVisible() && locator.isEnabled()) {
				locator.click(new Locator.ClickOptions().setForce(true));
				waitForNetworkIdle(page);
			} else {
				logger.warn("Element not clickable: {}", selector.getValue());
			}
		} catch (Exception e) {
			logger.error("Failed to click the element: {}", selector.getValue(), e);
			throw e; // Rethrow the exception for proper error handling
		}
	}

	// Selects a dropdown value by visible text
	public static void selectDropdownValue(Page page, Selector selector, String value) {
		Locator locator = getLocator(page, selector);
		if (locator.isVisible()) {
			locator.selectOption(value);
		} else {
			logger.warn("Dropdown not visible: {}", selector.getValue());
		}
	}

	// Checks if the specified text is present in an element
	public static boolean isTextPresent(Page page, Selector selector, String expectedText) {
		String actualText = getText(page, selector).trim();

		if (actualText.isBlank() || actualText.isEmpty()) {
			actualText = page.locator(selector.getValue()).getAttribute("value");
		}

		if (actualText == null || expectedText == null || !actualText.trim().contains(expectedText.trim())) {
			throw new AssertionError(
					"Expected text '" + expectedText + "' was not found. Actual text: '" + actualText + "'");
		}

		logger.info("Text '" + expectedText + "' is present in the element.");
		return actualText != null && actualText.contains(expectedText);
	}

	public static boolean isTextPresent(Page page, Selector selector, String expectedText, int index) {
		String actualText = getTextByIndex(page, selector, index).trim();

		if (actualText.isBlank() || actualText.isEmpty()) {
			actualText = page.locator(selector.getValue()).nth(index).getAttribute("value");
		}

		if (actualText == null || expectedText == null || !actualText.trim().contains(expectedText.trim())) {
			throw new AssertionError(
					"Expected text '" + expectedText + "' was not found. Actual text: '" + actualText + "'");
		}

		logger.info("Text '" + expectedText + "' is present in the element.");
		return actualText != null && actualText.trim().contains(expectedText.trim());
	}

	// Hovers over an element
	public static void hoverOverElement(Page page, Selector selector) {
		Locator locator = getLocator(page, selector);
		if (locator.isVisible()) {
			locator.hover();
		} else {
			logger.warn("Element not visible for hover: {}", selector.getValue());
		}
	}

	// Clears an input field
	public static void clearInputField(Page page, Selector selector) {
		Locator locator = getLocator(page, selector);
		if (locator.isVisible()) {
			locator.clear();
		} else {
			logger.warn("Input field not visible to clear: {}", selector.getValue());
		}
	}

	// Method to check if an element is enabled
	public static boolean isElementEnabled(Page page, Selector selector) {
		return getLocator(page, selector).isEnabled();
	}

	// Method to check if an element is disabled
	public static boolean isElementDisabled(Page page, Selector selector) {
		return getLocator(page, selector).isDisabled();
	}

	public static boolean hasBeforePseudoElement(Page page, Selector locator) {
		String script = "(element) => window.getComputedStyle(element, '::before').content !== 'none'";

		boolean hasBefore = (boolean) page.evaluate(script, getLocator(page, locator).elementHandle());

		if (hasBefore) {
			System.out.println("Pseudo-element '::before' is present.");
		} else {
			System.out.println("Pseudo-element '::before' is not present.");
		}

		return hasBefore;
	}

	// Validate the state (enabled/disabled) of an element
	public static void validateElementState(Page page, Selector selector, Boolean expectedState,
			String stateDescription) {
		boolean isEnabled = isElementEnabled(page, selector); // Check if the element is enabled
		// Fail the test if the actual state doesn't match the expected state
		trueConditionCheck(String.format("The element should be %s, but it was %s.", stateDescription,
				isEnabled ? "enabled" : "disabled"), isEnabled == expectedState);
	}

	// Log step description and perform click action
	public static void logStepAndClick(Page page, String stepDescription, Selector selector) {
		AllureUtils.logStep(stepDescription);
		clickElement(page, selector);
	}

	public static void verifyNavigationSuccess(Page page, String expectedEndpoint, int timeoutInMillis) {
		logger.info("Waiting for the URL to contain '{}'.", expectedEndpoint);

		try {
			page.waitForURL(url -> {
				System.out.println("URL changed to: " + url);
				return url.contains(expectedEndpoint);
			}, new Page.WaitForURLOptions().setTimeout(timeoutInMillis));
			AllureUtils.logStep(String.format("Verifying if the page URL contains '%s'", expectedEndpoint));
			trueConditionCheck(
					String.format("Expected URL to contain '%s', but found: %s", expectedEndpoint, page.url()),
					page.url().contains(expectedEndpoint));
			AllureUtils.logStep("Navigation action performed successfully.");
			logger.info("Navigation success: The URL contains '{}'.", expectedEndpoint);

		} catch (Exception e) {
			logger.error("Failed to verify navigation to '{}'. Current URL: {}", expectedEndpoint, page.url(), e);
			throw e;
		}
	}

	// Retrieves the text content of an element and asserts the text matches the
	public static String getText(Page page, Selector selector) {
		try {
			waitForElement(page, selector);
			Locator locator = LocatorFactory.getLocator(page, selector);
			return locator.textContent().trim();
		} catch (PlaywrightException e) {
			System.err.println("Element not found or text could not be retrieved: " + e.getMessage());
			return "";
		}
	}

	// Retrieves the text content of an element and asserts the text matches the
	public static String getTextByIndex(Page page, Selector selector, int index) {
		try {
			Locator elements = page.locator(selector.getValue());
			elements.nth(index).waitFor(new Locator.WaitForOptions().setTimeout(ConfigReader.getTimeout()));
			waitForNetworkIdle(page);
			Locator locator = elements.nth(index);
			return locator.textContent().trim();
		} catch (PlaywrightException e) {
			System.err.println("Element not found or text could not be retrieved: " + e.getMessage());
			return "";
		}
	}

	// Retrieves the value of an attribute of an element
	public static String getElementAttribute(Page page, Selector selector, String attribute) {
		Locator locator = getLocator(page, selector);
		return locator.getAttribute(attribute);
	}

	public static void trueConditionCheck(String message, boolean condition) {
		System.out.println("Check: " + message + " | Result: " + condition);
		if (!condition) {
			throw new AssertionError(message);
		}
	}

	// Method to split a string by comma and store in a List<String>
	public static List<String> split(String value) {
		if (value == null || value.trim().isEmpty()) {
		}
		return Arrays.asList(value.split(";"));
	}

	public static void listValidation(Page page, Selector selector, List<String> list) {
		Locator locator = getLocator(page, selector);
		if (locator.count() == list.size()) {
			try {
				for (int i = 0; i < locator.count(); i++) {
					if (locator.nth(i).textContent().trim().equalsIgnoreCase(list.get(i).trim())) {
						scrollToView(page, locator.nth(i));
					} else {
						Assertions.fail("Expected text :" + list.get(i).trim() + "is missmatch actual text is "
								+ locator.nth(i).textContent().trim());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Assertions.fail("Expected Count :" + list.size() + "is missmatch actual Count is " + locator.count());
		}
	}

	// Method to check if a menu is completed
	public static boolean isMenuCompleted(Page page, String menuName) {
		String menuItemXPath = "//div[@class='sidebar-menu ']//li[not(.//button[@class='toggle-button'])]//a[text()='"
				+ menuName + "']";
		waitForElement(page, new Selector(SelectorType.XPATH, menuItemXPath));
		Locator menuItem = page.locator(menuItemXPath);
		if (menuItem.count() == 0) {
			System.out.println("Menu item not found: " + menuName);
			return false;
		}
		return menuItem.getAttribute("class").contains("completed");
	}

	public static void Uploiad(Page page, Selector selector, String fileName) {
		FileChooser fileChooser = page.waitForFileChooser(() -> getLocator(page, selector).click());
		fileChooser.setFiles(Paths.get(AppConstants.FILE_DATA_PATH + fileName));
	}

	public static int singleValueListValidation(Page page, Selector selector, String value) {
		int position = 0;
		Locator locator = getLocator(page, selector);
		waitForElement(page, selector);
		for (int i = 0; i < locator.count(); i++) {
			if (locator.nth(i).innerText().trim().equalsIgnoreCase(value)) {
				logger.info(value + " is present in the list....");
				return position = i;
			}
		}
		logger.error("{} is not found in the Options.", value);
		return position;
	}
}
