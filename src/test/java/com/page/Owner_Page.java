package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Owner_Page {

	public static String valCorporationorEntityUSBased = "//div[@id='question_42801']";
	public static Selector entityUSBased = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Is the Entity U.S. Based?']//parent::label//parent::div//following-sibling::div/label");
	public static Selector stateOfIncorporationDr = new Selector(SelectorType.XPATH,
			"(//label//span[normalize-space(text())='State of Incorporation']//parent::label//parent::div//following-sibling::div//div)[1]");
	public static Selector identificationType = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Identification Type']//parent::label//parent::div//following-sibling::div");
	public static Selector socialSecurityNumber = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Social Security Number']//parent::label//parent::div//following-sibling::div/input");
	public static Selector taxIDNumber = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Tax ID Number / EIN']//parent::label//parent::div//following-sibling::div/input");
	public static Selector entityName = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Entity Full Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector productAvailablePara = new Selector(SelectorType.XPATH, "//p[@class='app-font input-label']");

	public static String ownerUSCitizen = "//span[contains(normalize-space(text()),'citizen or resident alien?')]//parent::label//parent::div/parent::div";
	public static Selector valOwnerUSCitizen = new Selector(SelectorType.XPATH,
			"//span[contains(normalize-space(text()),'citizen or resident alien?')]//parent::label//parent::div//following-sibling::div/label");
	public static Selector employmentStatus = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Employment Status']//parent::label//parent::div//following-sibling::div");
	public static Selector selfEmployed = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Self-Employed')]//parent::label//parent::div//following-sibling::div//input");

	public static Selector occupation = new Selector(SelectorType.XPATH,
			" //label//span[contains(normalize-space(text()),'Occupation ')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector taxStatus = new Selector(SelectorType.XPATH,
			" //label//span[contains(normalize-space(text()),'Tax Status')]//parent::label//parent::div//following-sibling::div");
	public static Selector formOfIdentification = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Form of Identification')]//parent::label//parent::div//following-sibling::div");
	
	public static Selector relationshipToOwner = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Relationship to Owner')]//parent::label//parent::div//following-sibling::div");
	
	
	public static Selector formOfIdentificationother = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'Form of Identification')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'Other (please specify)')]] //parent::div//following-sibling::div/input)");
	public static Selector numberOnIdentification = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Number on Identification')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector stateCountryOfIssuance = new Selector(SelectorType.XPATH,
			"//label[span[normalize-space(text())='State or Country of Issuance' or normalize-space(text())='State / Country of Issuance']]/ancestor::div/following-sibling::div/input");
	public static Selector identificationExpirationDate = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Identification Expiration Date')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector anotherLegalDocumentation = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),' another person with legal documentation')]//parent::label//parent::div//following-sibling::div");
	public static Selector currentResidence = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Current Residence']//parent::label//parent::div//following-sibling::div/div");
	public static Selector currentResidenceOther = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'Current Residence')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'Other (please specify)')]] //parent::div//following-sibling::div/input)");

	public static Selector guardianPrefix = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[normalize-space(text())='Prefix']//parent::label//parent::div//following-sibling::div");
	public static Selector guardianFirstName = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[normalize-space(text())='First Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector guardianInitial = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[contains(normalize-space(text()), 'Middle Name') or contains(normalize-space(text()), 'Initial')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector guardianlastName = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[normalize-space(text())='Last Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector guardianSuffix = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[normalize-space(text())='Suffix']//parent::label//parent::div//following-sibling::div");
	public static Selector guardianPhoneNumber = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[normalize-space(text())='Phone Number']//parent::label//parent::div//following-sibling::div/input");
	public static Selector guardianEmailAddress = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[normalize-space(text())='Email Address']//parent::label//parent::div//following-sibling::div/input");
	public static Selector guardianEmailAddressConfirmation = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()), 'Guardian')]/parent::div/following-sibling::div//label/span[normalize-space(text())='Email Address Confirmation' or normalize-space(text())='Email Confirmation']/parent::label/parent::div/following-sibling::div/input");

	public static Selector guardianFormOfIdentification = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[contains(normalize-space(text()),'Form of Identification')]//parent::label//parent::div//following-sibling::div/div");
	public static Selector guardianFormOfIdentificationother = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//span[contains(normalize-space(text()),'Form of Identification')]//parent::label//parent::div/parent::div/parent::div//following-sibling::div//label//span[normalize-space(text())='Other (please specify)']//parent::label//parent::div//following-sibling::div/input");
	public static Selector guardianNumberOnIdentification = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[contains(normalize-space(text()),'Number on Identification')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector guardianStateCountryOfIssuance = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[contains(normalize-space(text()),'State or Country of Issuance')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector guardianIdentificationExpirationDate = new Selector(SelectorType.XPATH,
			"//h3[contains(normalize-space(text()),'Guardian')]/parent::div//following-sibling::div//label//span[contains(normalize-space(text()),'Identification Expiration Date')]//parent::label//parent::div//following-sibling::div//input");

	public static String trustIdentificationType = "//div[@id='question_43526']";
	public static Selector trustFormationDate = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Trust Formation Date']//parent::label//parent::div//following-sibling::div//input");
	public static Selector trustFullName = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Trust Full Name']//parent::label//parent::div//following-sibling::div//input");
	
	
	
	
	
	
}
