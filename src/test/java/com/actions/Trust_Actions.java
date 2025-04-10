package com.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.base.BaseAction;
import com.config.ConfigReader;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.page.CommonElements;
import com.page.Owner_Page;
import com.page.Trust_Page;
import com.utils.AllureUtils;
import com.utils.ErrorHandler;

public class Trust_Actions {

	public static void trustFlow(Page page, Map<String, String> rowData) {
		try {
			page.waitForTimeout(ConfigReader.getTimeout());
			if (rowData.get("Type of Ownership").equalsIgnoreCase("Trust")) {
				AllureUtils.logStep("proceeding with Trust Information ");
				boolean notCompleted = BaseAction.isMenuCompleted(page, "Trust");
				BaseAction.trueConditionCheck("section is not completed.",notCompleted == false);
				page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
						page.locator(Trust_Page.trustUSBased).elementHandle());
				BaseAction.selectByValue(page, Trust_Page.valTrustUSBased, rowData.get("Trust_U.S. based?"));
				if (AppConstants.NO.equalsIgnoreCase(rowData.get("Trust_U.S. based?"))) {
					BaseAction.isTextPresent(page, Owner_Page.productAvailablePara,
							AppConstants.PRODUCT_AVAILABILITY_MESSAGE);
				}
				BaseAction.drSelection(page, Trust_Page.typeOfTrust, rowData.get("Type_of_Trust"));
				BaseAction.drSelection(page, Trust_Page.governingState, rowData.get("Governing_State"));
				List<String> trusteeFormIdentification = BaseAction.split(rowData.get("Trustee_Form_of_Identification"));
				List<String> additionalTrustee = BaseAction.split(rowData.get("Additional_Trustee"));
				for (int i = 0; i < additionalTrustee.size(); i++) {
					BaseAction.fillInputFieldByIndex(page, Trust_Page.trusteeName, rowData.get("Trustee_Name"), i);
					BaseAction.fillInputFieldByIndex(page, Trust_Page.trusteeEmailAddress,
							rowData.get("Trustee_Email_Address"), i);
					BaseAction.fillInputFieldByIndex(page, Trust_Page.trusteePhoneNumber,
							rowData.get("Trustee_Phone_Number"), i);
					BaseAction.drSelection(page, Trust_Page.trusteeFormIdentification, trusteeFormIdentification.get(i));
					if(trusteeFormIdentification.get(i).equalsIgnoreCase(AppConstants.OTHER)) {
						BaseAction.fillInputFieldByIndex(page, Trust_Page.formOfIdentificationother, rowData.get("Trustee_Form_of_Identification_Other"), i);
					}
					BaseAction.fillInputFieldByIndex(page, Trust_Page.trusteeNumberonIdentification,
							rowData.get("Trustee_Number_on_Identification"), i);
					BaseAction.fillInputFieldByIndex(page, Trust_Page.trusteeStateorCountryIssuance,
							rowData.get("Trustee_State_or_Country_Issuance"), i);
					BaseAction.fillInputFieldByIndex(page, Trust_Page.trusteeIdentificationExpirationDate,
							rowData.get("Trustee_Identification_Expiration_Date"), i);
					BaseAction.selectByValue(page, Trust_Page.Additionaltrustee, additionalTrustee.get(i));
				}

				BaseAction.selectByValueContain(page, Trust_Page.trustAgreement, rowData.get("Trust_Agreement"));
				if (rowData.get("Trust_Agreement").toLowerCase().contains(AppConstants.OTHER)) {
					BaseAction.fillInputField(page, Trust_Page.describeOther, rowData.get("Describe_Other"));
				}
				BaseAction.clickElement(page, CommonElements.Proceed);
				page.pause();
			}
		} catch (Exception e) {
			ErrorHandler.handleError("Trust section.", e, page);
		}
	}
}
