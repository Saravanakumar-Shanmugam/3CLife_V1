package com.actions;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.BaseAction;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.page.CommonElements;
import com.utils.AllureUtils;
import com.utils.ErrorHandler;

public class Started_Product {

	private static final Logger logger = LoggerFactory.getLogger(Started_Product.class);

	public static void StartedProduct(Page page, Map<String, String> rowData) {
		try {
			logger.info("proceeding with Let's Get Started / Product section");
			AllureUtils.logStep("proceeding with Let's Get Started / Product section");
			boolean notCompleted = BaseAction.isMenuCompleted(page, "Product");
			BaseAction.assertTrueCondition(notCompleted == false, "section is not completed.");
			BaseAction.waitForElement(page, CommonElements.bannerTitle);
			BaseAction.listValidation(page, CommonElements.bannerTitle, BaseAction.split(rowData.get("Dashboard-title")));
			BaseAction.waitForElement(page, com.page.Started_Product.productDropdown);
			BaseAction.drSelection(page, com.page.Started_Product.productDropdown, rowData.get("Product Type"));
			BaseAction.drSelection(page, com.page.Started_Product.termSelectionDropdown, rowData.get("Term Selection"));
			if (rowData.get("Resubmitted or Replaced").equalsIgnoreCase(AppConstants.YES)) {
				BaseAction.fillInputField(page, com.page.Started_Product.policyNumber, "001002");
			}

			String TypeofOwnership = rowData.get("Type of Ownership");
			BaseAction.drSelection(page, com.page.Started_Product.typeOfOwnershipDropdown, TypeofOwnership);
			if (TypeofOwnership.equalsIgnoreCase("Other Entity")) {
				BaseAction.fillInputField(page, com.page.Started_Product.describeOtherEntity,
						rowData.get("Describe Other Entity"));
			}
			if (TypeofOwnership.equalsIgnoreCase("Trust")) {
				boolean boolval = BaseAction.isTextPresent(page, com.page.Started_Product.accountDesignation,
						TypeofOwnership);
				BaseAction.assertTrueCondition(boolval,
						"if the Type of Ownership is Trust means by default 9the Account Designation  is also selected as Trust but it was selected as : "
								+ BaseAction.getText(page, com.page.Started_Product.accountDesignation));
				if (!rowData.get("Account Designation").equalsIgnoreCase("Trust")) {
					BaseAction.drSelection(page, com.page.Started_Product.accountDesignation,
							rowData.get("Account Designation"));
				}
			} else {
				BaseAction.drSelection(page, com.page.Started_Product.accountDesignation,
						rowData.get("Account Designation"));
			}
			logger.info("Dropdown value is : " + BaseAction.getText(page, com.page.Started_Product.accountDesignation));
			BaseAction.drSelection(page, com.page.Started_Product.planTypeDropdown, rowData.get("Plan Type"));
			List<String> splitValues = BaseAction.split(rowData.get("Writing_Agent_Split%"));
			logger.info("Total no of agent added : " + splitValues.size());
			for (int i = 0; i < splitValues.size(); i++) { // Changed from <= to <
				if (i < splitValues.size()) {
					BaseAction.fillInputFieldByIndex(page, com.page.Started_Product.WritingCode,
							BaseAction.split(rowData.get("Writing_Agent_Code")).get(i), i);
					BaseAction.fillInputFieldByIndex(page, com.page.Started_Product.agentFirstName,
							BaseAction.split(rowData.get("Writing Agent_First Name")).get(i), i);
					BaseAction.fillInputFieldByIndex(page, com.page.Started_Product.agentLastName,
							BaseAction.split(rowData.get("Writing Agent_Last Name")).get(i), i);
					BaseAction.fillInputFieldByIndex(page, com.page.Started_Product.agentEmail,
							BaseAction.split(rowData.get("Writing_Agent_Email_Address")).get(i), i);
					BaseAction.fillInputFieldByIndex(page, com.page.Started_Product.Split, splitValues.get(i), i);
					BaseAction.selectByValue(page, com.page.Started_Product.getSecondaryAgentOptions(i),
							BaseAction.split(rowData.get("additional Agent")).get(i));
				}
			}
			String totalvalue = BaseAction.getElementAttribute(page, com.page.Started_Product.totalSplit, "value");
			BaseAction.assertTrueCondition(totalvalue.equalsIgnoreCase("100"), "Total Split % is " + totalvalue);
			BaseAction.clickElement(page, CommonElements.next);
		} catch (Exception e) {
			ErrorHandler.handleError("Started_Product", e, page);
		}
	}

	public static void requiredValidation(Page page, Map<String, String> rowData) {
		try {
			BaseAction.clickElement(page, CommonElements.next);
			page.waitForTimeout(2000);
			List<String> splitValues = BaseAction.split(rowData.get("Started_Product_Required"));
			BaseAction.listValidation(page, CommonElements.error, splitValues);
		} catch (Exception e) {
			ErrorHandler.handleError("Started_Product", e, page);
		}
	}
	
	public static void optionValidations(Page page, Map<String, String> rowData) {
		try {
			Map<String, String> data = ExcelTestDataUtil.getData().get("TestCase-No:1");
			BaseAction.clickElement(page, com.page.Started_Product.termSelectionDropdown);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Term Selection")));
			BaseAction.listValidation(page, com.page.Started_Product.resubmittedApplicationYNOptions, BaseAction.split(rowData.get("Resubmitted or Replaced")));
			BaseAction.clickElement(page, com.page.Started_Product.typeOfOwnershipDropdown);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Type of Ownership")));
			BaseAction.selectByValue(page, BaseAction.options, data.get("Type of Ownership"));
			BaseAction.clickElement(page, com.page.Started_Product.planTypeDropdown);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Plan Type")));
			BaseAction.clickElement(page, com.page.Started_Product.accountDesignation);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Account Designation")));
			BaseAction.listValidation(page, com.page.Started_Product.getSecondaryAgentOptions(0), BaseAction.split(rowData.get("additional Agent")));
		} catch (Exception e) {
			ErrorHandler.handleError("Started_Product", e, page);
		}
	}
}
