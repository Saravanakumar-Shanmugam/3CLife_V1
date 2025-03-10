package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Documents_Signature_Page {

	//Documents

	public static Selector uploadDocument = new Selector(SelectorType.XPATH, "//input[@type='file']//parent::div");
	public static Selector documentName = new Selector(SelectorType.XPATH, "//div[text()='visibility']//parent::div//parent::div//span");
	public static Selector visibility = new Selector(SelectorType.XPATH, "//div[text()='visibility']");
	public static Selector delete = new Selector(SelectorType.XPATH, "//div[text()='delete']");

	// Signature
	public static Selector city=new Selector(SelectorType.XPATH,"//label//span[contains(normalize-space(text()),'City')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector State=new Selector(SelectorType.XPATH,"//label//span[normalize-space(text())='State']//parent::label//parent::div//following-sibling::div");
	public static Selector  reasonForSigning=new Selector(SelectorType.XPATH,"//label//span[contains(normalize-space(text()),'reason for signing')]//parent::label//parent::div//following-sibling::div");
	public static Selector timePeriod = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()),'Time period')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector explainReason = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()),'Please explain the reason:')]//parent::label//parent::div//following-sibling::div/input");
	
	
	public static Selector completeSignature = new Selector(SelectorType.XPATH, "//button[text()='Complete Signature']");
	public static Selector signHere_SignatureLabels = new Selector(SelectorType.XPATH, "//div[contains(@class,'signature-status')]//div[span and button]//span");
	public static Selector signHere_SignatureButtons = new Selector(SelectorType.XPATH, "//div[contains(@class,'signature-status')]//div[span and button]//button");
	public static Selector mailSent_SignatureLabels = new Selector(SelectorType.XPATH, "//div[contains(@class,'signature-status')]//div[not(button)]//span");

	public static Selector Submit = new Selector(SelectorType.XPATH, "//button[text()='Submit']");

	public static Selector docusignStart = new Selector(SelectorType.XPATH, "//span[text()='Start']//parent::button");
	public static Selector docusignSign = new Selector(SelectorType.XPATH, "//div[contains(@class,'signature-tab-content')]");
	public static Selector docusignFinish = new Selector(SelectorType.XPATH, "//button[text()='Finish']");

	//Adopt Your Signature

	public static Selector fullName = new Selector(SelectorType.XPATH, "//label[normalize-space(text())='Full Name']//parent::div//following-sibling::div//input");
	public static Selector Initials = new Selector(SelectorType.XPATH, "//label[normalize-space(text())='Initials']//parent::div//following-sibling::div//input");

	public static Selector adoptSign = new Selector(SelectorType.XPATH, "//span[text()='Adopt and Sign']//parent::button");
	public static Selector Cancel = new Selector(SelectorType.XPATH, "//span[text()='Cancel']//parent::button");

}
