package com.actions;

import java.util.List;
import java.util.Map;

import com.base.BaseAction;
import com.config.ConfigReader;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.page.CommonElements;
import com.page.Owner_Page;
import com.utils.AllureUtils;
import com.utils.ErrorHandler;

public class Owner_Actions {

	public static void ownerInformationOther(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Other Owner Information ");
		page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
				page.locator(Owner_Page.valCorporationorEntityUSBased).elementHandle());
		BaseAction.selectByValue(page, Owner_Page.entityUSBased, rowData.get("Entity U.S. Based?"));
		page.waitForTimeout(ConfigReader.getTimeout());
		if (AppConstants.NO.equalsIgnoreCase(rowData.get("Entity U.S. Based?"))) {
			BaseAction.isTextPresent(page, Owner_Page.productAvailablePara, AppConstants.US_ONLY_OWNER_MESSAGE);
		}
		BaseAction.drSelection(page, Owner_Page.stateOfIncorporationDr, rowData.get("State of Incorporation"));
		BaseAction.selectByValue(page, Owner_Page.identificationType, rowData.get("Identification_Type"));
		if ("ssn".equalsIgnoreCase(rowData.get("Identification_Type"))
				|| "Social Security Number".equalsIgnoreCase(rowData.get("Identification_Type"))) {
			BaseAction.typeInputField(page, Owner_Page.socialSecurityNumber, rowData.get("Social Security Number"));
		} else {
			BaseAction.typeInputField(page, Owner_Page.taxIDNumber, rowData.get("Tax ID Number"));
		}
		BaseAction.fillInputField(page, Owner_Page.entityName, rowData.get("Entity Name"));
		fillOwnerDetails(page, rowData);
		fillResidentialAddress(page, rowData);
		BaseAction.clickElement(page, CommonElements.next);
	}

	private static void fillOwnerDetails(Page page, Map<String, String> rowData) {
		BaseAction.fillInputField(page, CommonElements.firstName, rowData.get("Owner_First Name"));
		BaseAction.fillInputField(page, CommonElements.lastName, rowData.get("Owner_Last Name"));
		BaseAction.typeInputField(page, CommonElements.phoneNumber, rowData.get("Owner_Phone Number"));
		BaseAction.fillInputField(page, CommonElements.emailAddress, rowData.get("Owner_Email Address"));
	}

	private static void fillResidentialAddress(Page page, Map<String, String> rowData) {
		BaseAction.fillInputField(page, CommonElements.residenceStreet1, rowData.get("Owner_Residence_Street 1"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.drSelectionContain(page, rowData.get("Owner_Residence_City"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.fillInputField(page, CommonElements.residenceStreet2, rowData.get("Owner_Residence_Street 2"));
		BaseAction.isTextPresent(page, CommonElements.residenceState, rowData.get("Owner_Residence_State"));
		BaseAction.isTextPresent(page, CommonElements.residenceCity, rowData.get("Owner_Residence_City"));
		BaseAction.isTextPresent(page, CommonElements.residenceZipCode, rowData.get("Owner_Residence_Zip Code"));
		BaseAction.drSelection(page, Owner_Page.currentResidence, rowData.get("Owner_Current Residence"));
		if (AppConstants.OTHER.equalsIgnoreCase(rowData.get("Owner_Current Residence"))) {
			BaseAction.fillInputField(page, Owner_Page.currentResidenceOther,
					rowData.get("Owner_Current Residence Other"));
		}
	}

	private static void fillMailingAddress(Page page, Map<String, String> rowData) {
		BaseAction.fillInputField(page, CommonElements.mailingStreet1, rowData.get("Owner_Mailing_Street 1"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.drSelectionContain(page, rowData.get("Owner_Mailing_City"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.fillInputField(page, CommonElements.mailingStreet2, rowData.get("Owner_Mailing_Street 2"));
		BaseAction.isTextPresent(page, CommonElements.mailingState, rowData.get("Owner_Mailing_State"));
		BaseAction.isTextPresent(page, CommonElements.mailingCity, rowData.get("Owner_Mailing_City"));
		BaseAction.isTextPresent(page, CommonElements.mailingZipCode, rowData.get("Owner_Mailing_Zip Code"));
	}

	public static void ownerInformationPerson(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Person Owner Information ");
		page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
				page.locator(Owner_Page.ownerUSCitizen).elementHandle());
		BaseAction.selectByValue(page, Owner_Page.valOwnerUSCitizen, rowData.get("Owner US Citizen"));
		page.waitForTimeout(ConfigReader.getTimeout());
		if (AppConstants.NO.equalsIgnoreCase(rowData.get("Owner US Citizen"))) {
			BaseAction.isTextPresent(page, Owner_Page.productAvailablePara, AppConstants.US_ONLY_OWNER_MESSAGE);
		}
		fillOwnerpersonDetails(page, rowData);
		BaseAction.selectByValue(page, Owner_Page.anotherLegalDocumentation,
				rowData.get("Owner_Guardian / Power of Attorney Information"));
		if (!AppConstants.NO.equalsIgnoreCase(rowData.get("Owner_Guardian / Power of Attorney Information").trim())) {
			guardianFillOwnerpersonDetails(page, rowData);
		}
		fillResidentialAddress(page, rowData);
		BaseAction.selectByValue(page, CommonElements.sameAsResidentialAddress,
				rowData.get("same as the Residential Address?"));
		if (AppConstants.NO.equalsIgnoreCase(rowData.get("same as the Residential Address?"))) {
			fillMailingAddress(page, rowData);
		}
		BaseAction.clickElement(page, CommonElements.next);
;	}

	public static void ownerInformationTrust(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Trust Owner Information ");
		page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
				page.locator(Owner_Page.trustIdentificationType).elementHandle());
		BaseAction.selectByValue(page, Owner_Page.identificationType, rowData.get("Identification_Type"));
		if ("ssn".equalsIgnoreCase(rowData.get("Identification_Type"))
				|| "Social Security Number".equalsIgnoreCase(rowData.get("Identification_Type"))) {
			BaseAction.typeInputField(page, Owner_Page.socialSecurityNumber, rowData.get("Social Security Number"));
		} else {
			BaseAction.typeInputField(page, Owner_Page.taxIDNumber, rowData.get("Tax ID Number"));
		}
		BaseAction.datePicker(page, Owner_Page.trustFormationDate, rowData.get("Trust Formation Date"));
		BaseAction.typeInputField(page, Owner_Page.trustFullName, rowData.get("Trust Full Name"));
		fillMailingAddress(page, rowData);
		BaseAction.clickElement(page, CommonElements.next);
	}

	public static void ownerInformation(Page page, Map<String, String> rowData) {
		try {
			boolean notCompleted = BaseAction.isMenuCompleted(page, "Owner");
			BaseAction.assertTrueCondition(notCompleted == false, "section is not completed.");
			if ("Natural Person".equalsIgnoreCase(rowData.get("Type of Ownership"))
					|| "Joint".equalsIgnoreCase(rowData.get("Type of Ownership"))) {
				ownerInformationPerson(page, rowData);
			} else if ("Other Entity".equalsIgnoreCase(rowData.get("Type of Ownership"))) {
				ownerInformationOther(page, rowData);
			} else if ("Trust".equalsIgnoreCase(rowData.get("Type of Ownership"))) {
				ownerInformationTrust(page, rowData);
			}
		} catch (Exception e) {
			ErrorHandler.handleError("Owner section.", e, page);
		}
	}

	private static void fillOwnerpersonDetails(Page page, Map<String, String> rowData) {
		BaseAction.typeInputField(page, CommonElements.socialSecurityNumber, rowData.get("Social Security Number"));
		BaseAction.datePicker(page, CommonElements.dateOfBirth, rowData.get("Owner_ODB"));
		BaseAction.drSelection(page, CommonElements.gender, rowData.get("Owner_Gender"));
		BaseAction.drSelection(page, CommonElements.prefix, rowData.get("Owner_Prefix"));
		BaseAction.fillInputField(page, CommonElements.firstName, rowData.get("Owner_First Name"));
		BaseAction.fillInputField(page, CommonElements.initial, rowData.get("Owner_Initial"));
		BaseAction.fillInputField(page, CommonElements.lastName, rowData.get("Owner_Last Name"));
		BaseAction.drSelection(page, CommonElements.suffix, rowData.get("Owner_Suffix"));
		BaseAction.drSelection(page, CommonElements.maritalStatus, rowData.get("Owner_Marital Status"));
		BaseAction.drSelection(page, Owner_Page.employmentStatus, rowData.get("Owner_Employment Status"));
		if ("Self-Employed".equalsIgnoreCase(rowData.get("Owner_Employment Status"))) {
			BaseAction.fillInputField(page, Owner_Page.selfEmployed, rowData.get("Owner_Self-employed"));
		}
		BaseAction.fillInputField(page, Owner_Page.occupation, rowData.get("Owner_Occupation"));
		BaseAction.drSelection(page, Owner_Page.taxStatus, rowData.get("Owner_Tax Status"));
		BaseAction.typeInputField(page, CommonElements.phoneNumber, rowData.get("Owner_Phone Number"));
		BaseAction.fillInputField(page, CommonElements.emailAddress, rowData.get("Owner_Email Address"));
		BaseAction.fillInputField(page, CommonElements.emailAddressConfirmation, rowData.get("Owner_Email Address"));
		BaseAction.drSelection(page, Owner_Page.formOfIdentification, rowData.get("Owner_Form of Identification"));
		if (AppConstants.OTHER.equalsIgnoreCase(rowData.get("Owner_Form of Identification"))) {
			BaseAction.fillInputField(page, Owner_Page.formOfIdentificationother, rowData.get("Owner_Other"));
		}
		BaseAction.fillInputField(page, Owner_Page.numberOnIdentification,
				rowData.get("Owner_Number on Identification"));
		BaseAction.fillInputField(page, Owner_Page.stateCountryOfIssuance,
				rowData.get("Owner_State / Country of Issuance"));
		BaseAction.datePicker(page, Owner_Page.identificationExpirationDate,
				rowData.get("Owner_Identification Expiration Date"));
	}

	private static void guardianFillOwnerpersonDetails(Page page, Map<String, String> rowData) {
		BaseAction.drSelection(page, Owner_Page.guardianPrefix, rowData.get("Owner_Guardian_Prefix"));
		BaseAction.fillInputField(page, Owner_Page.guardianFirstName, rowData.get("Owner_Guardian_First Name"));
		BaseAction.fillInputField(page, Owner_Page.guardianInitial, rowData.get("Owner_Guardian_Initial"));
		BaseAction.fillInputField(page, Owner_Page.guardianlastName, rowData.get("Owner_Guardian_Last Name"));
		BaseAction.drSelection(page, Owner_Page.guardianSuffix, rowData.get("Owner_Guardian_Suffix"));
		BaseAction.typeInputField(page, Owner_Page.guardianPhoneNumber, rowData.get("Owner_Guardian_Phone Number"));
		BaseAction.fillInputField(page, Owner_Page.guardianEmailAddress, rowData.get("Owner_Guardian_Email Address"));
		BaseAction.fillInputField(page, Owner_Page.guardianEmailAddressConfirmation,
				rowData.get("Owner_Guardian_Email Address"));
		BaseAction.drSelection(page, Owner_Page.guardianFormOfIdentification,
				rowData.get("Owner_Guardian_Form of Identification"));
		if (AppConstants.OTHER.equalsIgnoreCase(rowData.get("Owner_Guardian_Form of Identification"))) {
			BaseAction.fillInputField(page, Owner_Page.guardianFormOfIdentificationother,
					rowData.get("Owner_Guardian_Other"));
		}
		BaseAction.fillInputField(page, Owner_Page.guardianNumberOnIdentification,
				rowData.get("Owner_Guardian_Number on Identification"));
		BaseAction.fillInputField(page, Owner_Page.guardianStateCountryOfIssuance,
				rowData.get("Owner_Guardian_State / Country of Issuance"));
		BaseAction.datePicker(page, Owner_Page.guardianIdentificationExpirationDate,
				rowData.get("Owner_Guardian_Identification Expiration Date"));
	}

	public static void requiredValidation(Page page, Map<String, String> rowData) {
		try {
			if ("Natural Person".equalsIgnoreCase(rowData.get("Type of Ownership"))
					|| "Joint".equalsIgnoreCase(rowData.get("Type of Ownership"))) {
				page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
						page.locator(Owner_Page.ownerUSCitizen).elementHandle());
			} else if ("Other Entity".equalsIgnoreCase(rowData.get("Type of Ownership"))) {
				page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
						page.locator(Owner_Page.valCorporationorEntityUSBased).elementHandle());
			} else if ("Trust".equalsIgnoreCase(rowData.get("Type of Ownership"))) {
				page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
						page.locator(Owner_Page.trustIdentificationType).elementHandle());
			}
			BaseAction.clickElement(page, CommonElements.next);
			page.waitForTimeout(ConfigReader.getTimeout());
			List<String> splitValues = BaseAction.split(rowData.get("Owner"));
			BaseAction.listValidation(page, CommonElements.error, splitValues);
		} catch (Exception e) {
			ErrorHandler.handleError("Owner section.", e, page);
		}
	}
	
	public static void optionValidations(Page page, Map<String, String> rowData) {
		try {
			if ("Natural Person".equalsIgnoreCase(rowData.get("Type of Ownership"))
					|| "Joint".equalsIgnoreCase(rowData.get("Type of Ownership"))) {
				BaseAction.listValidation(page, Owner_Page.valOwnerUSCitizen, BaseAction.split(rowData.get("Owner US Citizen")));
			} else if ("Other Entity".equalsIgnoreCase(rowData.get("Type of Ownership"))) {
				BaseAction.listValidation(page, Owner_Page.entityUSBased, BaseAction.split(rowData.get("Entity U.S. Based?")));
			} else if ("Trust".equalsIgnoreCase(rowData.get("Type of Ownership"))) {
				BaseAction.listValidation(page, Owner_Page.identificationType, BaseAction.split(rowData.get("Identification_Type")));
			}
			BaseAction.clickElement(page, CommonElements.gender);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Owner_Gender")));
			BaseAction.clickElement(page, CommonElements.prefix);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Owner_Prefix")));
			BaseAction.clickElement(page, CommonElements.suffix);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Owner_Suffix")));
			BaseAction.clickElement(page, CommonElements.maritalStatus);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Owner_Marital Status")));
			BaseAction.clickElement(page, Owner_Page.employmentStatus);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Owner_Employment Status")));
			BaseAction.clickElement(page, Owner_Page.taxStatus);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Owner_Tax Status")));
			BaseAction.clickElement(page, Owner_Page.formOfIdentification);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Owner_Form of Identification")));
			BaseAction.listValidation(page, Owner_Page.anotherLegalDocumentation, BaseAction.split(rowData.get("Owner_Guardian / Power of Attorney Information")));
			BaseAction.clickElement(page, Owner_Page.currentResidence);
			BaseAction.listValidation(page, BaseAction.options, BaseAction.split(rowData.get("Owner_Current Residence")));
			BaseAction.listValidation(page, CommonElements.sameAsResidentialAddress, BaseAction.split(rowData.get("same as the Residential Address?")));
		} catch (Exception e) {
			ErrorHandler.handleError("Started_Product", e, page);
		}
	}
}
