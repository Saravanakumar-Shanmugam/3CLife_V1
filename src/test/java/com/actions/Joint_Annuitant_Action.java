package com.actions;

import java.util.Map;

import com.base.BaseAction;
import com.config.ConfigReader;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.page.Annuitant_Page;
import com.page.CommonElements;
import com.page.Owner_Page;
import com.utils.AllureUtils;
import com.utils.ErrorHandler;

public class Joint_Annuitant_Action {

	public static void jointAnnuitan(Page page, Map<String, String> rowData) {
		try {
			if (rowData.get("Plan Type").equalsIgnoreCase("Non-Qualified")) {
				AllureUtils.logStep("proceeding with Joint Annuitant Information ");
				BaseAction.selectByValue(page, Annuitant_Page.jointAnnuitantSameOwner,
						rowData.get("Joint Annuitant Same as Owner"));
				if (rowData.get("Joint Annuitant Same as Owner").toLowerCase()
						.contains(AppConstants.NO.toLowerCase())) {
					BaseAction.clickElement(page, CommonElements.Proceed);
					page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
							page.locator(Annuitant_Page.valAnnuitantUSCitizen).elementHandle());
					BaseAction.selectByValue(page, Annuitant_Page.annuitantUSCitizen,
							rowData.get("Joint_Annuitant a US citizen"));
					if (AppConstants.NO.equalsIgnoreCase(rowData.get("Joint_Annuitant a US citizen"))) {
						page.waitForTimeout(2000);
						BaseAction.isTextPresent(page, Owner_Page.productAvailablePara,
								AppConstants.US_ONLY_JOINT_ANNUITANT_MESSAGE);
					}
					fillAnnuitantDetails(page, rowData);
					fillResidentialAddress(page, rowData);
					BaseAction.selectByValue(page, CommonElements.sameAsResidentialAddress,
							rowData.get("Joint_Annuitant_same as the Residential Address"));
					if (AppConstants.NO
							.equalsIgnoreCase(rowData.get("Joint_Annuitant_same as the Residential Address"))) {
						fillMailingAddress(page, rowData);
					}
				}
			}
		} catch (Exception e) {
			ErrorHandler.handleError("Joint Annuitant Section..", e, page);
		}
	}

	private static void fillAnnuitantDetails(Page page, Map<String, String> rowData) {
		BaseAction.datePicker(page, CommonElements.dateOfBirth, rowData.get("Joint_Annuitant_Date of Birth"));
		BaseAction.drSelection(page, CommonElements.gender, rowData.get("Joint_Annuitant_Gender"));
		BaseAction.typeInputField(page, CommonElements.socialSecurityNumber, rowData.get("Joint_Annuitant_SSN"));
		BaseAction.drSelection(page, CommonElements.prefix, rowData.get("Joint_Annuitant_Prefix"));
		BaseAction.fillInputField(page, CommonElements.firstName, rowData.get("Joint_Annuitant_First Name"));
		BaseAction.fillInputField(page, CommonElements.initial, rowData.get("Joint_Annuitant_Initial"));
		BaseAction.fillInputField(page, CommonElements.lastName, rowData.get("Joint_Annuitant_Last Name"));
		BaseAction.drSelection(page, CommonElements.suffix, rowData.get("Joint_Annuitant_Suffix"));
		BaseAction.drSelection(page, CommonElements.maritalStatus, rowData.get("Joint_Annuitant_Marital Status"));
		BaseAction.typeInputField(page, CommonElements.phoneNumber, rowData.get("Joint_Annuitant_Phone Number"));
		BaseAction.fillInputField(page, CommonElements.emailAddress, rowData.get("Joint_Annuitant_Email Address"));
		BaseAction.fillInputField(page, CommonElements.emailAddressConfirmation,
				rowData.get("Joint_Annuitant_Email Address"));
		BaseAction.drSelection(page, Annuitant_Page.relationshipToOwner,
				rowData.get("Joint_Annuitant_Relationship to Owner"));

	}

	private static void fillResidentialAddress(Page page, Map<String, String> rowData) {
		BaseAction.fillInputField(page, CommonElements.residenceStreet1,
				rowData.get("Joint_Annuitant_Residence_Street 1"));
		BaseAction.drSelectionContain(page, rowData.get("Joint_Annuitant_Residence_City"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.fillInputField(page, CommonElements.residenceStreet2,
				rowData.get("Joint_Annuitant_Residence_Street 2"));
		BaseAction.isTextPresent(page, CommonElements.residenceState, rowData.get("Joint_Annuitant_Residence_State"));
		BaseAction.isTextPresent(page, CommonElements.residenceCity, rowData.get("Joint_Annuitant_Residence_City"));
		BaseAction.isTextPresent(page, CommonElements.residenceZipCode,
				rowData.get("Joint_Annuitant_Residence_Zip Code"));
	}

	private static void fillMailingAddress(Page page, Map<String, String> rowData) {
		BaseAction.fillInputField(page, CommonElements.mailingStreet1, rowData.get("Joint_Annuitant_Mailing_Street 1"));
		BaseAction.drSelectionContain(page, rowData.get("Joint_Annuitant_Mailing_City"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.fillInputField(page, CommonElements.mailingStreet2, rowData.get("Joint_Annuitant_Mailing_Street 2"));
		BaseAction.isTextPresent(page, CommonElements.mailingState, rowData.get("Joint_Annuitant_Mailing_State"));
		BaseAction.isTextPresent(page, CommonElements.mailingCity, rowData.get("Joint_Annuitant_Mailing_City"));
		BaseAction.isTextPresent(page, CommonElements.mailingZipCode, rowData.get("Joint_Annuitant_Mailing_Zip Code"));
	}

	public static void trustjointAnnuitan(Page page, Map<String, String> rowData) {
		page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
				page.locator(Annuitant_Page.valAnnuitantUSCitizen).elementHandle());
		BaseAction.selectByValue(page, Annuitant_Page.annuitantUSCitizen, rowData.get("Joint_Annuitant a US citizen"));
		if (AppConstants.NO.equalsIgnoreCase(rowData.get("Joint_Annuitant a US citizen"))) {
			page.waitForTimeout(ConfigReader.getTimeout());
			BaseAction.isTextPresent(page, Owner_Page.productAvailablePara,
					AppConstants.US_ONLY_JOINT_ANNUITANT_MESSAGE);
		}
		fillAnnuitantDetails(page, rowData);
		fillResidentialAddress(page, rowData);
		BaseAction.selectByValue(page, CommonElements.sameAsResidentialAddress,
				rowData.get("Joint_Annuitant_same as the Residential Address"));
		if (AppConstants.NO.equalsIgnoreCase(rowData.get("Joint_Annuitant_same as the Residential Address"))) {
			fillMailingAddress(page, rowData);
		}
	}

	public static void jointAnnuitantFlow(Page page, Map<String, String> rowData) {
		page.waitForTimeout(ConfigReader.getTimeout());
		page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
				page.locator(Annuitant_Page.isThereJointAnnuitantQuestion).elementHandle());
		BaseAction.selectByValue(page, Annuitant_Page.isThereJointAnnuitant, rowData.get("Is there a Joint Annuitant"));
		if (rowData.get("Is there a Joint Annuitant").equalsIgnoreCase(AppConstants.YES)) {
			if (rowData.get("Type of Ownership").equalsIgnoreCase("Trust")) {
				trustjointAnnuitan(page, rowData);
			} else {
				jointAnnuitan(page, rowData);
			}
		}
		BaseAction.clickElement(page, CommonElements.Proceed);
	}
}
