package com.actions;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.BaseAction;
import com.config.ConfigReader;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.page.CommonElements;
import com.page.Owner_Page;
import com.utils.AllureUtils;
import com.utils.ErrorHandler;

public class Joint_Owne_Action {

	public static final Logger logger = LoggerFactory.getLogger(Joint_Owne_Action.class);

	public static void jointOwnerInformationPerson(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Person Owner Information ");
		page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
				page.locator(Owner_Page.ownerUSCitizen).elementHandle());
		BaseAction.selectByValue(page, Owner_Page.valOwnerUSCitizen, rowData.get("joint_Owner US Citizen"));
		page.waitForTimeout(ConfigReader.getTimeout());
		if (AppConstants.NO.equalsIgnoreCase(rowData.get("joint_Owner US Citizen"))) {
			BaseAction.isTextPresent(page, Owner_Page.productAvailablePara, AppConstants.US_ONLY_OWNER_MESSAGE);
		}
		fillJointOwnerpersonDetails(page, rowData);
		BaseAction.selectByValue(page, Owner_Page.anotherLegalDocumentation,
				rowData.get("joint_Owner_Guardian / Power of Attorney Information"));
		if (!AppConstants.NO.equalsIgnoreCase(rowData.get("Owner_Guardian / Power of Attorney Information").trim())) {
			guardianFillOwnerpersonDetails(page, rowData);
		}
		fillResidentialAddress(page, rowData);
		BaseAction.selectByValue(page, CommonElements.sameAsResidentialAddress,
				rowData.get("joint_Owner same as the Residential Address?"));
		if (AppConstants.NO.equalsIgnoreCase(rowData.get("same as the Residential Address?"))) {
			fillMailingAddress(page, rowData);
		}
		BaseAction.clickElement(page, CommonElements.Proceed);
	}

	private static void fillJointOwnerpersonDetails(Page page, Map<String, String> rowData) {

		BaseAction.selectByValue(page, CommonElements.identificationType, rowData.get("joint_Owner_Identification_Type"));
		if (rowData.get("joint_Owner_Identification_Type").equalsIgnoreCase("Social Security Number")) {
			BaseAction.typeInputField(page, CommonElements.socialSecurityNumber, rowData.get("joint_Owner_Social Security Number"));
		} else if (rowData.get("joint_Owner_Identification_Type").equalsIgnoreCase("Tax ID Number")) {
			BaseAction.typeInputField(page, CommonElements.taxIDNumber, rowData.get("joint_Owner_Tax ID Number"));
		} else {
			logger.info("Identification value is not matched with any options...");
		}
		BaseAction.datePicker(page, CommonElements.dateOfBirth, rowData.get("joint_Owner_ODB"));
		BaseAction.drSelection(page, CommonElements.gender, rowData.get("joint_Owner_Gender"));
		BaseAction.drSelection(page, CommonElements.prefix, rowData.get("joint_Owner_Prefix"));
		BaseAction.fillInputField(page, CommonElements.firstName, rowData.get("joint_Owner_First Name"));
		BaseAction.fillInputField(page, CommonElements.initial, rowData.get("joint_Owner_Initial"));
		BaseAction.fillInputField(page, CommonElements.lastName, rowData.get("joint_Owner_Last Name"));
		BaseAction.drSelection(page, CommonElements.suffix, rowData.get("joint_Owner_Suffix"));
		BaseAction.drSelection(page, CommonElements.maritalStatus, rowData.get("joint_Owner_Marital Status"));
		BaseAction.drSelection(page, Owner_Page.employmentStatus, rowData.get("joint_Owner_Employment Status"));
		if ("Self-Employed".equalsIgnoreCase(rowData.get("joint_Owner_Employment Status"))) {
			BaseAction.fillInputField(page, Owner_Page.selfEmployed, rowData.get("joint_Owner_Self-employed"));
		}
		BaseAction.fillInputField(page, Owner_Page.occupation, rowData.get("joint_Owner_Occupation"));
		BaseAction.typeInputField(page, CommonElements.phoneNumber, rowData.get("joint_Owner_Phone Number"));
		BaseAction.fillInputField(page, CommonElements.emailAddress, rowData.get("joint_Owner_Email Address"));
		BaseAction.fillInputField(page, CommonElements.emailAddressConfirmation, rowData.get("joint_Owner_Email Address"));
		
		BaseAction.drSelection(page, Owner_Page.relationshipToOwner, rowData.get("joint_Owner_Relationship to Owner"));

		
		BaseAction.drSelection(page, Owner_Page.formOfIdentification, rowData.get("joint_Owner_Form of Identification"));
		if (AppConstants.OTHER.equalsIgnoreCase(rowData.get("joint_Owner_Form of Identification"))) {
			BaseAction.fillInputField(page, Owner_Page.formOfIdentificationother, rowData.get("joint_Owner_Other"));
		}
		BaseAction.fillInputField(page, Owner_Page.numberOnIdentification,
				rowData.get("joint_Owner_Number on Identification"));
		BaseAction.fillInputField(page, Owner_Page.stateCountryOfIssuance,
				rowData.get("joint_Owner_State / Country of Issuance"));
		BaseAction.datePicker(page, Owner_Page.identificationExpirationDate,
				rowData.get("joint_Owner_Identification Expiration Date"));
	}

	private static void guardianFillOwnerpersonDetails(Page page, Map<String, String> rowData) {
		BaseAction.drSelection(page, Owner_Page.guardianPrefix, rowData.get("joint_Owner_Guardian_Prefix"));
		BaseAction.fillInputField(page, Owner_Page.guardianFirstName, rowData.get("joint_Owner_Guardian_First Name"));
		BaseAction.fillInputField(page, Owner_Page.guardianInitial, rowData.get("joint_Owner_Guardian_Initial"));
		BaseAction.fillInputField(page, Owner_Page.guardianlastName, rowData.get("joint_Owner_Guardian_Last Name"));
		BaseAction.drSelection(page, Owner_Page.guardianSuffix, rowData.get("joint_Owner_Guardian_Suffix"));
		BaseAction.typeInputField(page, Owner_Page.guardianPhoneNumber, rowData.get("joint_Owner_Guardian_Phone Number"));
		BaseAction.fillInputField(page, Owner_Page.guardianEmailAddress, rowData.get("joint_Owner_Guardian_Email Address"));
		BaseAction.fillInputField(page, Owner_Page.guardianEmailAddressConfirmation,
				rowData.get("joint_Owner_Guardian_Email Address"));
		BaseAction.drSelection(page, Owner_Page.guardianFormOfIdentification,
				rowData.get("joint_Owner_Guardian_Form of Identification"));
		if (AppConstants.OTHER.equalsIgnoreCase(rowData.get("joint_Owner_Guardian_Form of Identification"))) {
			BaseAction.fillInputField(page, Owner_Page.guardianFormOfIdentificationother,
					rowData.get("joint_Owner_Guardian_Other"));
		}
		BaseAction.fillInputField(page, Owner_Page.guardianNumberOnIdentification,
				rowData.get("joint_Owner_Guardian_Number on Identification"));
		BaseAction.fillInputField(page, Owner_Page.guardianStateCountryOfIssuance,
				rowData.get("joint_Owner_Guardian_State / Country of Issuance"));
		BaseAction.datePicker(page, Owner_Page.guardianIdentificationExpirationDate,
				rowData.get("joint_Owner_Guardian_Identification Expiration Date"));
	}

	private static void fillResidentialAddress(Page page, Map<String, String> rowData) {
		BaseAction.fillInputField(page, CommonElements.residenceStreet1, rowData.get("joint_Owner_Residence_Street 1"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.drSelectionContain(page, rowData.get("joint_Owner_Residence_City"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.fillInputField(page, CommonElements.residenceStreet2, rowData.get("joint_Owner_Residence_Street 2"));
//		BaseAction.isTextPresent(page, CommonElements.residenceState, rowData.get("joint_Owner_Residence_State"));
		
		BaseAction.drSelection(page, CommonElements.residenceState, "Alaska");
		
		BaseAction.isTextPresent(page, CommonElements.residenceCity, rowData.get("joint_Owner_Residence_City"));
		BaseAction.isTextPresent(page, CommonElements.residenceZipCode, rowData.get("joint_Owner_Residence_Zip Code"));
		BaseAction.drSelection(page, Owner_Page.currentResidence, rowData.get("joint_Owner_Current Residence"));
		if (AppConstants.OTHER.equalsIgnoreCase(rowData.get("joint_Owner_Current Residence"))) {
			BaseAction.fillInputField(page, Owner_Page.currentResidenceOther,
					rowData.get("joint_Owner_Current Residence Other"));
		}
	}

	private static void fillMailingAddress(Page page, Map<String, String> rowData) {
		BaseAction.fillInputField(page, CommonElements.mailingStreet1, rowData.get("joint_Owner_Mailing_Street 1"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.drSelectionContain(page, rowData.get("joint_Owner_Mailing_City"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.fillInputField(page, CommonElements.mailingStreet2, rowData.get("joint_Owner_Mailing_Street 2"));
//		BaseAction.isTextPresent(page, CommonElements.mailingState, rowData.get("joint_Owner_Mailing_State"));
		
		BaseAction.drSelection(page, CommonElements.mailingState, "Alaska");

		
		BaseAction.isTextPresent(page, CommonElements.mailingCity, rowData.get("joint_Owner_Mailing_City"));
		BaseAction.isTextPresent(page, CommonElements.mailingZipCode, rowData.get("joint_Owner_Mailing_Zip Code"));
	}

	public static void jointOwnerInformation(Page page, Map<String, String> rowData) {
		try {
			if ("Joint".equalsIgnoreCase(rowData.get("Type of Ownership"))) {
				boolean notCompleted = BaseAction.isMenuCompleted(page, "Joint Owner");
				BaseAction.trueConditionCheck("section is not completed.", notCompleted == false);
				jointOwnerInformationPerson(page, rowData);
			}
		} catch (Exception e) {
			ErrorHandler.handleError("Owner section.", e, page);
		}
	}
}
