package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Additional_Information_Page {

	public static Selector consentElectronicallyDocuments = new Selector(SelectorType.XPATH, "//div[@id='question_43799']//div[@class='custom-radio mb-2 ']//label");
	public static Selector deliveryPolicyTo = new Selector(SelectorType.XPATH, "//div[@id='question_43800']//div[@class='custom-radio mb-2 ']//label");
	public static Selector existinglifeInsurance = new Selector(SelectorType.XPATH, "//div[@id='question_40716']//div[@class='custom-radio mb-2 ']//label");
	public static Selector replaceOrChange = new Selector(SelectorType.XPATH, "//div[@id='question_40717']//div[@class='custom-radio mb-2 ']//label");
	public static Selector existingPolicyOrContract = new Selector(SelectorType.XPATH, "//div[@id='question_45737']//div//input");
}
