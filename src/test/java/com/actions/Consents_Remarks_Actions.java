package com.actions;

import java.util.Map;

import com.base.BaseAction;
import com.microsoft.playwright.Page;
import com.page.CommonElements;
import com.page.Consents_Remarks_Page;
import com.utils.AllureUtils;

public class Consents_Remarks_Actions {
	
	public static void Consents(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Suitability Consents Information ");
		BaseAction.selectByValue(page, Consents_Remarks_Page.consentElectronicDeliveryDocuments, rowData.get(""));
		BaseAction.selectByValue(page, Consents_Remarks_Page.deliveryPolicy, rowData.get(""));
		BaseAction.clickElement(page, CommonElements.next);
	}
	
	public static void remarks(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Suitability Remarks Information ");
		BaseAction.typeInputField(page, Consents_Remarks_Page.remarks, rowData.get("Remarks"));
		BaseAction.clickElement(page, CommonElements.next);

	}
}
