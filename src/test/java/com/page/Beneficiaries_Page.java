package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Beneficiaries_Page {

	public static Selector primaryBeneficiaries = new Selector(SelectorType.XPATH, "//div//h3[contains(normalize-space(text()), 'Primary Beneficiaries')]/parent::div/following-sibling::div/span[normalize-space(text()) != 'info' and @class='font-weight-bold text-black']");
	public static Selector contingentBeneficiaries = new Selector(SelectorType.XPATH, "//div//h3[contains(normalize-space(text()), 'Contingent Beneficiaries')]/parent::div/following-sibling::div/span[normalize-space(text()) != 'info' and @class='font-weight-bold text-black']");
	public static Selector addBeneficiary = new Selector(SelectorType.XPATH, "//button[text()='Add Beneficiary']");
	public static Selector personOrEntity = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()), 'Person or an Entity?')]//parent::label//parent::div//following-sibling::div/label");
	public static Selector beneficiaryType = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()), 'Beneficiary Type')]//parent::label//parent::div//following-sibling::div/label");

	public static Selector dateOfBirth = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Date of Birth']//parent::label//parent::div//following-sibling::div/div/input");
	public static Selector percentage = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Percentage']//parent::label//parent::div//following-sibling::div/input");
	public static Selector relationshipToOwner = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Relationship to Owner']//parent::label//parent::div//following-sibling::div");

	public static Selector identificationType  = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Identification Type']//parent::label//parent::div//following-sibling::div/label");
	public static Selector taxIDNumber = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Tax ID Number / EIN']//parent::label//parent::div//following-sibling::div/input");
	public static Selector socialSecurityNumber = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Social Security Number']//parent::label//parent::div//following-sibling::div/input");

	public static Selector formationDate  = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Formation Date']//parent::label//parent::div//following-sibling::div/div/input");
	public static Selector fullName  = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Full Name']//parent::label//parent::div//following-sibling::div//input");

	public static Selector street1  = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()),'Street Line 1')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector street2  = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()),'Street Line 2')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector state  = new Selector(SelectorType.XPATH, "(//label//span[contains(normalize-space(text()),'State')]//parent::label//parent::div//following-sibling::div)[1]");
	public static Selector City  = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()),'City')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector zipCode  = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()),'Zip Code')]//parent::label//parent::div//following-sibling::div//input");
}
