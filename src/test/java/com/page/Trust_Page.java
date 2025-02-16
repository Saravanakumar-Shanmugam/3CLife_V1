package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Trust_Page {

	public static Selector typeOfTrust = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Type of Trust']//parent::label//parent::div//following-sibling::div/div");
	public static Selector governingState = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Governing State']//parent::label//parent::div//following-sibling::div/div");
	public static Selector trustAgreement = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()),'Trust Agreement requires')]//parent::label//parent::div//following-sibling::div");
	public static Selector describeOther = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Other (please specify)']//parent::label//parent::div//following-sibling::div/input");
	public static Selector trusteeEmailAddress	 = new Selector(SelectorType.XPATH, "//tr/td[count(//th[text()='Trustee Email Address']/preceding-sibling::th)+1]//input");
	public static Selector trusteeName	 = new Selector(SelectorType.XPATH, "//tr/td[count(//th[text()='Trustee Name']/preceding-sibling::th)+1]//input");
	public static Selector trusteePhoneNumber	 = new Selector(SelectorType.XPATH, "//tr/td[count(//th[text()='Trustee Phone Number']/preceding-sibling::th)+1]//input");
	public static Selector trusteeAction	 = new Selector(SelectorType.XPATH, "//tr/td[count(//th[text()='Action']/preceding-sibling::th)+1]//div/*");
	public static Selector trusteeAddPerson	 = new Selector(SelectorType.XPATH, "//button[text()='Add Person']");

	public static String trustUSBased = "//label//span[contains(normalize-space(text()),'Trust considered to be U.S. based?')]//parent::label//parent::div//parent::div[@id]";
	public static Selector valTrustUSBased = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Is the Trust considered to be U.S. based?']//parent::label//parent::div//following-sibling::div/label");
	
}
