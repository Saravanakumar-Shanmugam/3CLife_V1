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

public class Annuitant_Actions {

	public static void annuitant(Page page, Map<String, String> rowData) {
		try {
			AllureUtils.logStep("proceeding with Annuitant Information ");
			page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
					page.locator(Annuitant_Page.valAnnuitantSameOwner).elementHandle());
			BaseAction.selectByValue(page, Annuitant_Page.annuitantSameOwner, rowData.get("Annuitant same Owner"));
			if (rowData.get("Annuitant same Owner").toLowerCase().contains(AppConstants.NO.toLowerCase())) {
				BaseAction.clickElement(page, CommonElements.next);
				boolean notCompleted = BaseAction.isMenuCompleted(page, "Annuitant");
				BaseAction.assertTrueCondition(notCompleted == false, "section is not completed.");
				page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
						page.locator(Annuitant_Page.valAnnuitantUSCitizen).elementHandle());

				BaseAction.selectByValue(page, Annuitant_Page.annuitantUSCitizen,
						rowData.get("Annuitant a US citizen"));
				if (AppConstants.NO.equalsIgnoreCase(rowData.get("Annuitant a US citizen"))) {
					page.waitForTimeout(ConfigReader.getTimeout());
					BaseAction.isTextPresent(page, Owner_Page.productAvailablePara,
							AppConstants.US_ONLY_ANNUITANT_MESSAGE);
				}
				fillAnnuitantDetails(page, rowData);
				fillResidentialAddress(page, rowData);
				BaseAction.selectByValue(page, CommonElements.sameAsResidentialAddress,
						rowData.get("same as the Residential Address"));
				if (AppConstants.NO.equalsIgnoreCase(rowData.get("same as the Residential Address"))) {
					fillMailingAddress(page, rowData);
				}
			}
			BaseAction.clickElement(page, CommonElements.next);
		} catch (Exception e) {
			ErrorHandler.handleError("Annuitant Section.", e, page);
		}
	}

	private static void fillAnnuitantDetails(Page page, Map<String, String> rowData) {
		BaseAction.datePicker(page, CommonElements.dateOfBirth, rowData.get("Annuitant_Date of Birth"));
		BaseAction.drSelection(page, CommonElements.gender, rowData.get("Annuitant_Gender"));
		BaseAction.typeInputField(page, CommonElements.socialSecurityNumber, rowData.get("Annuitant_SSN"));
		BaseAction.drSelection(page, CommonElements.prefix, rowData.get("Annuitant_Prefix"));
		BaseAction.fillInputField(page, CommonElements.firstName, rowData.get("Annuitant_First Name"));
		BaseAction.fillInputField(page, CommonElements.initial, rowData.get("Annuitant_Initial"));
		BaseAction.fillInputField(page, CommonElements.lastName, rowData.get("Annuitant_Last Name"));
		BaseAction.drSelection(page, CommonElements.suffix, rowData.get("Annuitant_Suffix"));
		BaseAction.drSelection(page, CommonElements.maritalStatus, rowData.get("Annuitant_Marital Status"));
		BaseAction.typeInputField(page, CommonElements.phoneNumber, rowData.get("Annuitant_Phone Number"));
		BaseAction.fillInputField(page, CommonElements.emailAddress, rowData.get("Annuitant_Email Address"));
		BaseAction.fillInputField(page, CommonElements.emailAddressConfirmation,
				rowData.get("Annuitant_Email Address"));
		BaseAction.drSelection(page, Annuitant_Page.relationshipToOwner,
				rowData.get("Annuitant_Relationship to Owner"));

	}

	private static void fillResidentialAddress(Page page, Map<String, String> rowData) {
		BaseAction.fillInputField(page, CommonElements.residenceStreet1, rowData.get("Annuitant_Residence_Street 1"));
		BaseAction.drSelectionContain(page, rowData.get("Annuitant_Residence_City"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.fillInputField(page, CommonElements.residenceStreet2, rowData.get("Annuitant_Residence_Street 2"));
		BaseAction.isTextPresent(page, CommonElements.residenceState, rowData.get("Annuitant_Residence_State"));
		BaseAction.isTextPresent(page, CommonElements.residenceCity, rowData.get("Annuitant_Residence_City"));
		BaseAction.isTextPresent(page, CommonElements.residenceZipCode, rowData.get("Annuitant_Residence_Zip Code"));
	}

	private static void fillMailingAddress(Page page, Map<String, String> rowData) {
		BaseAction.fillInputField(page, CommonElements.mailingStreet1, rowData.get("Annuitant_Mailing_Street 1"));
		BaseAction.drSelectionContain(page, rowData.get("Annuitant_Mailing_City"));
		page.waitForTimeout(ConfigReader.getTimeout());
		BaseAction.fillInputField(page, CommonElements.mailingStreet2, rowData.get("Annuitant_Mailing_Street 2"));
		BaseAction.isTextPresent(page, CommonElements.mailingState, rowData.get("Annuitant_Mailing_State"));
		BaseAction.isTextPresent(page, CommonElements.mailingCity, rowData.get("Annuitant_Mailing_City"));
		BaseAction.isTextPresent(page, CommonElements.mailingZipCode, rowData.get("Annuitant_Mailing_Zip Code"));
	}
	
	public static void trustAnnuitant(Page page, Map<String, String> rowData) {
		page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
				page.locator(Annuitant_Page.valAnnuitantUSCitizen).elementHandle());
				BaseAction.selectByValue(page, Annuitant_Page.annuitantUSCitizen,
						rowData.get("Annuitant a US citizen"));
				if (AppConstants.NO.equalsIgnoreCase(rowData.get("Annuitant a US citizen"))) {
					page.waitForTimeout(ConfigReader.getTimeout());
					BaseAction.isTextPresent(page, Owner_Page.productAvailablePara,
							AppConstants.US_ONLY_ANNUITANT_MESSAGE);
				}
				fillAnnuitantDetails(page, rowData);
				fillResidentialAddress(page, rowData);
				BaseAction.selectByValue(page, CommonElements.sameAsResidentialAddress,
						rowData.get("same as the Residential Address"));
				if (AppConstants.NO
						.equalsIgnoreCase(rowData.get("same as the Residential Address"))) {
					fillMailingAddress(page, rowData);
				}
				BaseAction.clickElement(page, CommonElements.next);
	}
	
	public static void annuitantFlow(Page page, Map<String, String> rowData) {
		if(rowData.get("Type of Ownership").equalsIgnoreCase("Trust")) {
			trustAnnuitant(page, rowData);
		}else {
			annuitant(page, rowData);
		}
	}
}
