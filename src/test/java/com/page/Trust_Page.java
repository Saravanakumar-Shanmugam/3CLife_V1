package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Trust_Page {

	public static Selector typeOfTrust = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Type of Trust']//parent::label//parent::div//following-sibling::div/div");
	public static Selector governingState = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Governing State']//parent::label//parent::div//following-sibling::div/div");
	public static Selector trustAgreement = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()),'Trust Agreement requires')]//parent::label//parent::div//following-sibling::div");
	public static Selector describeOther = new Selector(SelectorType.XPATH, "(//label[span[contains(normalize-space(text()), 'Trust Agreement requires')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'Other (please specify)')]] //parent::div//following-sibling::div/input)");
	public static Selector formOfIdentificationother = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'Form of Identification')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'Other (please specify)')]] //parent::div//following-sibling::div/input)");
	public static Selector trusteeEmailAddress	 = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Email Address']//parent::label//parent::div//following-sibling::div/input");
	public static Selector trusteeName	 = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Trustee Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector trusteePhoneNumber	 = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Phone Number']//parent::label//parent::div//following-sibling::div/input");
	public static Selector trusteeNumberonIdentification = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Number on Identification']//parent::label//parent::div//following-sibling::div/input");
	public static Selector trusteeStateorCountryIssuance	 = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='State or Country of Issuance']//parent::label//parent::div//following-sibling::div/input");
	public static Selector trusteeIdentificationExpirationDate	 = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Identification Expiration Date']//parent::label//parent::div//following-sibling::div//input");
	public static Selector trusteeFormIdentification = new Selector(SelectorType.XPATH, "//span[normalize-space()='Form of Identification']/parent::label/parent::div/following-sibling::div");
	public static Selector Additionaltrustee = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()),'additional Trustee?')]//parent::label//parent::div//following-sibling::div/label");

	
	public static String trustUSBased = "//label//span[contains(normalize-space(text()),'Trust considered to be U.S. based?')]//parent::label//parent::div//parent::div[@id]";
	public static Selector valTrustUSBased = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Is the Trust considered to be U.S. based?']//parent::label//parent::div//following-sibling::div/label");
	public static Selector productAvailablePara = new Selector(SelectorType.XPATH, "//p[@class='app-font input-label']");

}
