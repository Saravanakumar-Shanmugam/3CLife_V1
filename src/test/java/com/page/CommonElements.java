package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class CommonElements {

	public static Selector dateOfBirth = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Date of Birth']//parent::label//parent::div//following-sibling::div/div/input");
	public static Selector gender = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Gender']//parent::label//parent::div//following-sibling::div/div");
	public static Selector socialSecurityNumber = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Social Security Number']//parent::label//parent::div//following-sibling::div/input");
	public static Selector prefix = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Prefix']//parent::label//parent::div//following-sibling::div");
	public static Selector firstName = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='First Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector initial = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()), 'Middle Name') or contains(normalize-space(text()), 'Initial')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector lastName = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Last Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector suffix = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Suffix']//parent::label//parent::div//following-sibling::div");
	public static Selector maritalStatus = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Marital Status']//parent::label//parent::div//following-sibling::div");
	public static Selector phoneNumber = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Phone Number']//parent::label//parent::div//following-sibling::div/input");
	public static Selector emailAddress = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Email Address']//parent::label//parent::div//following-sibling::div/input");
	public static Selector emailAddressConfirmation = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Email Address Confirmation']//parent::label//parent::div//following-sibling::div/input");
	public static Selector sameAsResidentialAddress = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()), 'same as the Residential Address?')]//parent::label//parent::div//parent::div//following-sibling::div");
	public static Selector residenceStreet1 = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()), 'Residence Address')]//parent::div//label//span[contains(normalize-space(text()),'Street Line 1')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector residenceStreet2 = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()), 'Residence Address')]//parent::div//label//span[contains(normalize-space(text()),'Street Line 2')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector residenceState = new Selector(SelectorType.XPATH,
			"(//h3[contains(normalize-space(text()), 'Residence Address')]//parent::div//label//span[contains(normalize-space(text()),'State')]//parent::label//parent::div//following-sibling::div//div)[1]");
	public static Selector residenceCity = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()), 'Residence Address')]//parent::div//label//span[contains(normalize-space(text()),'City')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector residenceZipCode = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()), 'Residence Address')]//parent::div//label//span[contains(normalize-space(text()),'Zip Code')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector mailingStreet1 = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()), 'Mailing Address')]//parent::div//label//span[contains(normalize-space(text()),'Street Line 1')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector mailingStreet2 = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()), 'Mailing Address')]//parent::div//label//span[contains(normalize-space(text()),'Street Line 2')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector mailingState = new Selector(SelectorType.XPATH,
			"(//h3[contains(normalize-space(text()), 'Mailing Address')]//parent::div//label//span[contains(normalize-space(text()),'State')]//parent::label//parent::div//following-sibling::div//div)[1]");
	public static Selector mailingCity = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()), 'Mailing Address')]//parent::div//label//span[contains(normalize-space(text()),'City')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector mailingZipCode = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()), 'Mailing Address')]//parent::div//label//span[contains(normalize-space(text()),'Zip Code')]//parent::label//parent::div//following-sibling::div//input");


	public static Selector back = new Selector(SelectorType.XPATH, "//div//button[text()='Back']");
	public static Selector next = new Selector(SelectorType.XPATH, "//div//button[text()='Next']");
	public static Selector error = new Selector(SelectorType.CLASS, "message-block");
	public static Selector backToAppList = new Selector(SelectorType.CLASS, "back-icon");
	public static Selector save = new Selector(SelectorType.XPATH, "//div//button[text()='Save']");

	public static Selector pagepopuptext = new Selector(SelectorType.XPATH, "//div[@class='modal-body']//p");
	public static Selector stayOnPage = new Selector(SelectorType.XPATH, "//button[text()='STAY ON PAGE']");
	public static Selector proceedAnyways = new Selector(SelectorType.XPATH, "//button[text()='PROCEED ANYWAYS']");

}
