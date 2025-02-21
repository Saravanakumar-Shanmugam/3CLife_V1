package com.actions;

import java.util.Map;

import com.microsoft.playwright.Page;

public class App_Processor {

	public static void possitiveProcess(Page page,Map<String, String>rowData) {
		CreateApplication.createApplicationPopup(page, rowData);
		Started_Product.StartedProduct(page, rowData);
		Owner_Actions.ownerInformation(page, rowData);
		Trust_Actions.trustFlow(page, rowData);
		Annuitant_Actions.annuitantFlow(page, rowData);
		Joint_Annuitant_Action.jointAnnuitantFlow(page, rowData);
		Beneficiaries_Actions.beneficiariesFlow(page, rowData);
		Funding_Action.fundingFlow(page, rowData);
//		Suitability_Action.additionalInformation(page, rowData);
		Suitability_Action.financialResources(page, rowData);
		Suitability_Action.financialProfile(page, rowData);
		Suitability_Action.Replacements(page, rowData);
		Suitability_Action.ownerAgentStatements(page, rowData);
		Consents_Remarks_Actions.Consents(page, rowData);
		Consents_Remarks_Actions.remarks(page, rowData);
		Documents_Signature_Actions.uploadDocument(page, rowData);
		Documents_Signature_Actions.signature(page, rowData);
		
	}
}
