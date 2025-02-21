package com.actions;

import java.util.Map;

import com.base.BaseAction;
import com.microsoft.playwright.Page;
import com.page.CommonElements;
import com.page.Documents_Signature_Page;

public class Documents_Signature_Actions {

	public static void uploadDocument(Page page, Map<String, String> rowdata) {
//		BaseAction.fillInputField(page, Documents_Signature_Page.uploadDocument, "C:\\Users\\Premkumar A\\Pictures\\Google.png");
		BaseAction.Uploiad(page, Documents_Signature_Page.uploadDocument,rowdata.get("File Names"));
		BaseAction.clickElement(page, CommonElements.next);
	}
	
	public static void signature(Page page, Map<String, String> rowdata) {
		if (BaseAction.isElementEnabled(page, Documents_Signature_Page.completeSignature)) {
			BaseAction.clickElement(page, Documents_Signature_Page.completeSignature);
			int indexval = BaseAction.singleValueListValidation(page, Documents_Signature_Page.signHere_SignatureLabels, "For agent");
			BaseAction.clickElementByIndex(page, Documents_Signature_Page.signHere_SignatureButtons, indexval);
			page.pause();
		}else {
			
		}
	}

}
