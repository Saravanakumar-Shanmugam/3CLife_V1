package com.actions;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.BaseAction;
import com.config.ConfigReader;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.page.CommonElements;
import com.page.Documents_Signature_Page;
import com.utils.AllureUtils;

public class Documents_Signature_Actions {

	public static final Logger logger = LoggerFactory.getLogger(Documents_Signature_Actions.class);

	public static void uploadDocument(Page page, Map<String, String> rowdata) {
		page.waitForTimeout(ConfigReader.getTimeout());
		List<String> fileCount = BaseAction.split(rowdata.get("File Names"));
		for (int i = 0; i < fileCount.size(); i++) {
			BaseAction.Uploiad(page, Documents_Signature_Page.uploadDocument, fileCount.get(i));
			page.waitForTimeout(ConfigReader.getTimeout());
			BaseAction.clickElementByIndex(page, Documents_Signature_Page.documentTypeDr,i);
			page.waitForTimeout(ConfigReader.getTimeout());
			BaseAction.selectByValueByIndex(page, Documents_Signature_Page.documentTypeOptions, rowdata.get("Document Type"), i);
			page.waitForTimeout(ConfigReader.getTimeout());

		}
		BaseAction.clickElement(page, CommonElements.Proceed);
	}

	public static void signature(Page page, Map<String, String> rowdata) {
		page.waitForTimeout(ConfigReader.getTimeout());
		if ("Joint".equalsIgnoreCase(rowdata.get("Type of Ownership"))) {
			BaseAction.fillInputFieldByIndex(page, Documents_Signature_Page.city, rowdata.get("Owner_Signature_City"),
					0);
			BaseAction.drSelectionByIndex(page, Documents_Signature_Page.State, rowdata.get("Owner_Signature_State"),
					0);
			BaseAction.fillInputFieldByIndex(page, Documents_Signature_Page.city,
					rowdata.get("Joint_Owner_Signature_City"), 1);
			BaseAction.drSelectionByIndex(page, Documents_Signature_Page.State,
					rowdata.get("Joint_Owner_Signature_State"), 1);
		} else if (!"Trust".equalsIgnoreCase(rowdata.get("Type of Ownership"))) {
			BaseAction.fillInputField(page, Documents_Signature_Page.city, rowdata.get("Owner_Signature_City"));
			BaseAction.fillInputField(page, Documents_Signature_Page.State, rowdata.get("Owner_Signature_State"));
		}
		BaseAction.drSelection(page, Documents_Signature_Page.reasonForSigning, rowdata.get("reason_for_signing"));
		if (AppConstants.PRE_EXISTING_RELATIONSHIP.equalsIgnoreCase(rowdata.get("reason_for_signing"))) {
			BaseAction.fillInputField(page, Documents_Signature_Page.timePeriod, rowdata.get("Time_period"));
		} else if (AppConstants.OTHER.equalsIgnoreCase(rowdata.get("reason_for_signing"))) {
			BaseAction.fillInputField(page, Documents_Signature_Page.timePeriod,
					rowdata.get("Please_explain_the_reason"));
		}
		if (BaseAction.isElementEnabled(page, Documents_Signature_Page.completeSignature)) {

			BaseAction.clickElement(page, Documents_Signature_Page.completeSignature);
			int indexval = BaseAction.singleValueListValidation(page, Documents_Signature_Page.signHere_SignatureLabels,
					"For agent");
			BaseAction.clickElement(page, CommonElements.backToAppList);
			AllureUtils.attachVideo(page);
//			BaseAction.clickElementByIndex(page, Documents_Signature_Page.signHere_SignatureButtons, indexval);
//			Page agentdocpage = PlaywrightConfig.getContext().waitForPage(() -> BaseAction.clickElementByIndex(page,
//					Documents_Signature_Page.signHere_SignatureButtons, indexval));
//			while (BaseAction.isElementDisabled(agentdocpage, Documents_Signature_Page.docusignStart)) {
//				BaseAction.clickElement(agentdocpage, Documents_Signature_Page.docusignStart);
//				BaseAction.clickElement(agentdocpage, Documents_Signature_Page.docusignSign);
//			}
//			Page eapppage = PlaywrightConfig.getContext()
//					.waitForPage(() -> BaseAction.clickElement(agentdocpage, Documents_Signature_Page.docusignFinish));
		} else {
			logger.info("Signature button is disabled....");
		}
	}
}
