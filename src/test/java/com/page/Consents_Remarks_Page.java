package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Consents_Remarks_Page {
	
	// Consents

	public static Selector consentElectronicDeliveryDocuments = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'client consent to electronic delivery of documents')]]//parent::div//following-sibling::div/label");
	public static Selector deliveryPolicy = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'Delivery of Policy to')]]//parent::div//following-sibling::div/label");
	
	// Remarks

	public static Selector remarks = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'remarks ')]]//parent::div//following-sibling::div//textarea");
	
}
