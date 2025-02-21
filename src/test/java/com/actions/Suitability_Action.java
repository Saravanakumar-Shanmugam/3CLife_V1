package com.actions;

import java.util.List;
import java.util.Map;

import com.base.BaseAction;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.page.CommonElements;
import com.page.Suitability_Page;
import com.utils.AllureUtils;

public class Suitability_Action {

	public static void additionalInformation(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Suitability Additional Information Information ");
		page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
				page.locator(Suitability_Page.valCorporationorEntityUSBased).elementHandle());
		BaseAction.selectByValue(page, Suitability_Page.activeDutyPersonnel, rowData.get("active duty personnel"));
		BaseAction.clickElement(page, CommonElements.next);
		page.waitForTimeout(2000);
	}

	public static void financialResources(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Suitability Financial Resources and Objectives Information ");
		BaseAction.multiSelectByValue(page, Suitability_Page.incomeSources, rowData.get("income sources"));
		List<String> incomeSources = BaseAction.split(rowData.get("income sources"));
		for (int i = 0; i < incomeSources.size(); i++) {
			if (incomeSources.get(i).equalsIgnoreCase("Severance")) {
				BaseAction.typeInputField(page, Suitability_Page.amountReceivedFromSeverance, rowData.get("monthly amount received from Severance"));
				BaseAction.typeInputField(page, Suitability_Page.severanceExpectedToContinue, rowData.get("Severance income expected to continue"));
			} else if (incomeSources.get(i).equalsIgnoreCase("Disability")) {
				BaseAction.typeInputField(page, Suitability_Page.amountReceivedFromDisability, rowData.get("monthly amount received from Disability"));
				BaseAction.typeInputField(page, Suitability_Page.disabilityExpectedToContinue, rowData.get("Disability ncome expected to continue"));
			} else if (incomeSources.get(i).equalsIgnoreCase("Child Support")) {
				BaseAction.typeInputField(page, Suitability_Page.amountReceivedFromChild, rowData.get("monthly amount received from Child Support"));
				BaseAction.typeInputField(page, Suitability_Page.childExpectedToContinue, rowData.get("Child income expected to continue"));
			} else if (incomeSources.get(i).equalsIgnoreCase("Alimony")) {
				BaseAction.typeInputField(page, Suitability_Page.amountReceivedFromAlimony, rowData.get("monthly amount received from Alimony"));
				BaseAction.typeInputField(page, Suitability_Page.alimonyExpectedToContinue, rowData.get("Alimony income expected to continue"));
			} else if (incomeSources.get(i).equalsIgnoreCase("Unemployment")) {
				BaseAction.typeInputField(page, Suitability_Page.amountReceivedFromUnemployment, rowData.get("monthly amount received from Unemployment"));
				BaseAction.typeInputField(page, Suitability_Page.unemploymentExpectedToContinue, rowData.get("Unemployment income is expected to continue"));
			} else if (incomeSources.get(i).equalsIgnoreCase("Other")) {
				BaseAction.fillInputField(page, Suitability_Page.incomeSourcesOther, rowData.get("income sources Other"));
			}
		}
		BaseAction.drSelection(page, Suitability_Page.reverseMortgageOrHomEquityLoan, rowData.get("reverse mortgage"));
		BaseAction.multiSelectByValue(page, Suitability_Page.financialResources, rowData.get("financial resource"));
		List<String> financialResources = BaseAction.split(rowData.get("financial resource"));
		for (int i = 0; i < financialResources.size(); i++) {
			if (financialResources.get(i).equalsIgnoreCase("Family Member / Non-Family Member")) {
				BaseAction.fillInputField(page, Suitability_Page.familyMember, rowData.get("Family Member/Non-Family Member"));
			} else if (financialResources.get(i).equalsIgnoreCase("Other")) {
				BaseAction.fillInputField(page, Suitability_Page.financialResourcesOther, rowData.get("financial resource Other"));
			}
		}
		BaseAction.selectByValue(page, Suitability_Page.penaltiesAssociated, rowData.get("penalties associated"));
		BaseAction.selectByValue(page, Suitability_Page.contractualBenefitsAssociated, rowData.get("contractual benefits"));
		BaseAction.selectByValue(page, Suitability_Page.experienceLevelFinancialProduct, rowData.get("experience level with financial products"));
		BaseAction.selectByValue(page, Suitability_Page.generalRiskTolerance, rowData.get("general risk tolerance"));
		if (rowData.get("general risk tolerance").contains("Aggressive")) {
			BaseAction.fillInputField(page, Suitability_Page.aggressive, rowData.get("Aggressive"));
		}
		BaseAction.multiSelectByValue(page, Suitability_Page.financialObjectives, rowData.get("financial objectives"));
		List<String> financialObjectives = BaseAction.split(rowData.get("financial objectives"));
		for (int i = 0; i < financialObjectives.size(); i++) {
			if (financialObjectives.get(i).equalsIgnoreCase("Other")) {
				BaseAction.fillInputField(page, Suitability_Page.financialobjectivesOther, rowData.get("financial objectives Other"));
			}
		}
		BaseAction.selectByValue(page, Suitability_Page.selectedGuaranteeInterestPeriod, rowData.get("guarantee interest period"));
		BaseAction.multiSelectByValue(page, Suitability_Page.intendedUseAnyRiders, rowData.get("intended use of any riders"));
		BaseAction.multiSelectByValue(page, Suitability_Page.contractFeatures, rowData.get("contract features discussed"));
		BaseAction.clickElement(page, CommonElements.next);
		page.waitForTimeout(2000);
	}

	public static void financialProfile(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Suitability Financial Profile and Status  Information ");
		BaseAction.typeInputField(page, Suitability_Page.approximateHouseholdNetWorth, rowData.get("Approximate Household"));
		BaseAction.typeInputField(page, Suitability_Page.grossHouseholdAnnualIncome, rowData.get("Gross Household Annual Income"));
		BaseAction.selectByValue(page, Suitability_Page.expectChangeFutureIncome, rowData.get("expect a change in your future income"));
		if (rowData.get("expect a change in your future income").equalsIgnoreCase(AppConstants.YES)) {
			BaseAction.fillInputField(page, Suitability_Page.expectChangeFutureIncomeYes, rowData.get("expect a change in your future income If yes"));
		}
		BaseAction.selectByValue(page, Suitability_Page.amountPaidForAnnuity, rowData.get("amount being paid for this annuity"));
		BaseAction.clickElement(page, CommonElements.next);
		page.waitForTimeout(2000);
	}

	public static void Replacements(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Suitability Replacements Information ");
		page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
				page.locator(Suitability_Page.valReplacingAnnuity).elementHandle());
		BaseAction.selectByValue(page, Suitability_Page.replacingAnnuity, rowData.get("Are you replacing an annuity"));
		if (rowData.get("Are you replacing an annuity").equalsIgnoreCase(AppConstants.YES)) {
			BaseAction.selectByValue(page, Suitability_Page.annuityReplacedleast60Months, rowData.get("annuity being replaced for at least 60 months"));
			BaseAction.selectByValue(page, Suitability_Page.exchangedPast60Months, rowData.get("annuities replaced or exchanged within the past 60 months the same agent as this annuity"));
			BaseAction.selectByValue(page, Suitability_Page.replacedOrExchangedRncomeRide, rowData.get("annuities being replaced or exchanged have an income rider"));
			BaseAction.selectByValue(page, Suitability_Page.twoTieredAnnuityIncomePayout, rowData.get("annuities being replaced or exchanged a two-tiered annuity that provides and income payout"));
			BaseAction.typeInputField(page, Suitability_Page.guaranteedFixedInterestRate, rowData.get("guaranteed fixed interest rate"));
			BaseAction.selectByValue(page, Suitability_Page.deathBenefitRide, rowData.get("death benefit rider"));
			if (rowData.get("death benefit rider").equalsIgnoreCase(AppConstants.YES)) {
				BaseAction.typeInputField(page, Suitability_Page.deathBenefitRideYes, rowData.get("death benefit rider If Yes"));
			}
			BaseAction.selectByValue(page, Suitability_Page.withdrawaChargePeriod, rowData.get("annuities being replaced or exchanged still within its surrender"));
			if (rowData.get("annuities being replaced or exchanged still within its surrender").equalsIgnoreCase(AppConstants.YES)) {
				BaseAction.typeInputField(page, Suitability_Page.withdrawaCharge, rowData.get("withdrawal charge"));
				BaseAction.typeInputField(page, Suitability_Page.withdrawaValue, rowData.get("withdrawal value"));
				BaseAction.selectByValue(page, Suitability_Page.fullyConsideredSurrende, rowData.get("fully considered the surrender"));
			}
		}
		BaseAction.clickElement(page, CommonElements.next);
	}
	
	public static void ownerAgentStatements(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Suitability Owner + Agent Statements Information ");
		BaseAction.multiSelectByValue(page, Suitability_Page.selectAsMany, rowData.get("Select as many of the following that apply"));
		BaseAction.selectByValue(page, Suitability_Page.personOrRemote, rowData.get("Is the Owner in person or are they remote"));			
		BaseAction.selectByValue(page, Suitability_Page.submittingSupplemental, rowData.get("submitting supplemental suitability information"));			
		BaseAction.clickElement(page, CommonElements.next);
	}

}
