package com.actions;

import java.util.Map;

import com.base.BaseAction;
import com.config.ConfigReader;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.page.Additional_Information_Page;
import com.page.CommonElements;

public class Additional_Information_Action {

	public static void AdditionalInformation(Page page, Map<String, String> rowdMap) {
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.selectByValue(page, Additional_Information_Page.consentElectronicallyDocuments,
				rowdMap.get("consent_Electronically_Documents"));
		BaseAction.selectByValue(page, Additional_Information_Page.deliveryPolicyTo, rowdMap.get("delivery_Policy_To"));
		BaseAction.selectByValue(page, Additional_Information_Page.existinglifeInsurance,
				rowdMap.get("existing_life_Insurance"));
		BaseAction.selectByValue(page, Additional_Information_Page.replaceOrChange, rowdMap.get("replace_Or_Change"));
		if (rowdMap.get("replace_Or_Change").equalsIgnoreCase(AppConstants.YES)
				|| rowdMap.get("existing_life_Insurance").equalsIgnoreCase(AppConstants.YES)) {
			BaseAction.fillInputField(page, Additional_Information_Page.existingPolicyOrContract,
					rowdMap.get("existing_Policy_Or_Contract"));
		}
		BaseAction.clickElement(page, CommonElements.Proceed);
	}

}
