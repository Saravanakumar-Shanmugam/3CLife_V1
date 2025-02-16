package com.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.base.BaseAction;
import com.microsoft.playwright.Page;
import com.page.CommonElements;
import com.page.Trust_Page;
import com.utils.AllureUtils;
import com.utils.ErrorHandler;

public class Trust_Actions {

	public static void trustFlow(Page page, Map<String, String> rowData) {
		try {
			if (rowData.get("Type of Ownership").equalsIgnoreCase("Trust")) {
				AllureUtils.logStep("proceeding with Trust Information ");
				boolean notCompleted = BaseAction.isMenuCompleted(page, "Trust");
				BaseAction.assertTrueCondition(notCompleted == false, "section is not completed.");
				page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
						page.locator(Trust_Page.trustUSBased).elementHandle());
				BaseAction.selectByValue(page, Trust_Page.valTrustUSBased, rowData.get("Identification_Type"));
				BaseAction.drSelection(page, Trust_Page.typeOfTrust, rowData.get("Type_of_Trust"));
				BaseAction.drSelection(page, Trust_Page.governingState, rowData.get("Governing_State"));
				List<String> trusteeNames = BaseAction.split(rowData.get("Trustee_Name"));
				List<String> trusteeEmails = BaseAction.split(rowData.get("Trustee_Email_Address"));
				List<String> trusteePhoneNumbers = rowData.get("Trustee_Phone_Number") != null
						? BaseAction.split(rowData.get("Trustee_Phone_Number"))
						: new ArrayList<>(); // Handle null case with an empty list
				for (int i = 0; i < trusteeNames.size(); i++) {
					BaseAction.fillInputFieldByIndex(page, Trust_Page.trusteeName, trusteeNames.get(i), i);
					BaseAction.fillInputFieldByIndex(page, Trust_Page.trusteeEmailAddress, trusteeEmails.get(i), i);
					if (i < trusteePhoneNumbers.size()) {
						BaseAction.tYpeInputFieldByIndex(page, Trust_Page.trusteePhoneNumber,
								trusteePhoneNumbers.get(i), i);
					} else {
						System.out.println("Warning: Trustee phone number is missing for index " + i);
					}
					if (i < trusteeNames.size() - 1) {
						BaseAction.clickElement(page, Trust_Page.trusteeAddPerson);
					}
				}
				BaseAction.selectByValueContain(page, Trust_Page.trustAgreement, rowData.get("Trust_Agreement"));
				if (rowData.get("Trust_Agreement").toLowerCase().contains("other")) {
					BaseAction.fillInputField(page, Trust_Page.describeOther, rowData.get("Describe_Other"));
				}
				BaseAction.clickElement(page, CommonElements.next);
			}
		} catch (Exception e) {
			ErrorHandler.handleError("Trust section.", e, page);
		}
	}
}
