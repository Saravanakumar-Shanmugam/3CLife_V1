package com.actions;

import java.util.List;
import java.util.Map;

import com.base.BaseAction;
import com.config.ConfigReader;
import com.constants.AppConstants;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import com.page.CommonElements;
import com.page.Funding_Page;
import com.utils.AllureUtils;

public class Funding_Action {

	public static void fundingFlow(Page page, Map<String, String> rowData) {
		AllureUtils.logStep("proceeding with Funding Information ");
		boolean notCompleted = BaseAction.isMenuCompleted(page, "Owner");
		BaseAction.assertTrueCondition(notCompleted == false, "section is not completed.");
		page.waitForFunction("element => element.getAttribute('style') === 'background-color: white;'",
				page.locator(Funding_Page.totalexpectedPnitialPremiumVal).elementHandle());
		BaseAction.typeInputField(page, Funding_Page.totalexpectedPnitialPremium,
				rowData.get("total expected initial premium?"));
		BaseAction.isTextPresent(page, Funding_Page.totalCalExpInitialPremiumAmt, "$0");
		if (rowData.get("Proceed Anyways").equalsIgnoreCase(AppConstants.NO)) {
			List<String> source = BaseAction.split(rowData.get("Funding Source"));
			if (source.size() <= 5) {
				for (int i = 0; i < source.size(); i++) {
					BaseAction.clickElement(page, Funding_Page.addFunding);
//		   Payment
//		   Non-Qualified Transfer of Assets
//		   1035 Exchange
					if (rowData.get("Plan Type").equalsIgnoreCase("Non-Qualified")) {
						BaseAction.drSelection(page, Funding_Page.fundingSource, rowData.get("Funding Source"));
					} else {
//			   Contribution
//			   Direct Rollover
//			   Indirect Rollover
//			   Transfer

					}

//		   ACH
//		   Other
					if (rowData.get("Funding Source").equalsIgnoreCase("Payment")
							|| rowData.get("Funding Source").equalsIgnoreCase("Contribution")
							|| rowData.get("Funding Source").equalsIgnoreCase("Indirect Rollover")) {
						BaseAction.drSelection(page, Funding_Page.paymentForm, rowData.get("Payment Form"));
						if (rowData.get("Funding Source").equalsIgnoreCase("Contribution")
								&& !rowData.get("Plan Type").equalsIgnoreCase("Non-Qualified")) {
							BaseAction.typeInputField(page, Funding_Page.currentYearContribution,
									rowData.get("Current Year Contribution"));
							BaseAction.typeInputField(page, Funding_Page.priorYearContribution,
									rowData.get("Prior Year Contribution"));
						}
						if (rowData.get("Payment Form").equalsIgnoreCase("ACH")) {
							BaseAction.drSelection(page, Funding_Page.selectAccountOwner,
									rowData.get("Select Account Owner"));
							if (rowData.get("Select Account Owner").equalsIgnoreCase(AppConstants.OTHER)) {
								BaseAction.fillInputField(page, Funding_Page.accountOwnerName,
										rowData.get("Account Owner Name"));
								BaseAction.fillInputField(page, Funding_Page.accountOwnerEmail,
										rowData.get("Account Owner Email"));
							} else {

							}
							BaseAction.selectByValue(page, Funding_Page.accountType, rowData.get("Account Type"));
							BaseAction.typeInputField(page, Funding_Page.bankRouting,
									rowData.get("Bank Routing / ABA Number"));
							BaseAction.typeInputField(page, Funding_Page.bankName, rowData.get("Bank Name"));
							BaseAction.typeInputField(page, Funding_Page.accountNumber, rowData.get("Account Number"));
							BaseAction.typeInputField(page, Funding_Page.amount, rowData.get("Amount"));
						}
					} else {
//			 Replacement
						BaseAction.drSelection(page, Funding_Page.paymentForm, rowData.get("Payment Form"));
						BaseAction.fillInputField(page, Funding_Page.contractNumber,
								rowData.get("Surrendering Account Number"));
						BaseAction.fillInputField(page, Funding_Page.surrenderingCompanyName,
								rowData.get("Surrendering Company Name"));
						BaseAction.clickElement(page, Funding_Page.companySearch);
						page.waitForTimeout(ConfigReader.getTimeout());
						BaseAction.waitForElement(page, Funding_Page.ShrProcessingLocations);
						BaseAction.selectByValue(page, Funding_Page.ShrProcessingLocations,
								rowData.get("Processing Locations"));
						BaseAction.clickElement(page, Funding_Page.selectCompany);
						page.waitForTimeout(ConfigReader.getTimeout());
						BaseAction.waitForNetworkIdle(page);
						BaseAction.isTextPresent(page, Funding_Page.universalCarrierID,
								rowData.get("Universal Carrier ID"));
						BaseAction.isTextPresent(page, Funding_Page.universalLocationID,
								rowData.get("1035YP UniversalLocationID"));
						BaseAction.isTextPresent(page, Funding_Page.CompanyAddressLine1,
								rowData.get("Company Address Line 1"));
						BaseAction.isTextPresent(page, Funding_Page.CompanyAddressLine2,
								rowData.get("Company Address Line 2"));
						BaseAction.isTextPresent(page, Funding_Page.City, rowData.get("City"));
						BaseAction.isTextPresent(page, Funding_Page.State, rowData.get("State"));
						BaseAction.isTextPresent(page, Funding_Page.zipCode, rowData.get("Zip Code"));
						BaseAction.isTextPresent(page, Funding_Page.phoneNumber, rowData.get("Phone Number"));
						BaseAction.isTextPresent(page, Funding_Page.extension, rowData.get("Extension"));
						BaseAction.isTextPresent(page, Funding_Page.faxNumber, rowData.get("Fax Number"));
						BaseAction.fillInputField(page, Funding_Page.surrenderingProductType,
								rowData.get("Surrendering Product Type"));
						if (rowData.get("Plan Type").equalsIgnoreCase("Non-Qualified")) {
							// Non-Qualified.
						} else {
//				Traditional IRA
//				Inherited IRA
//				Roth IRA
//				Designated Roth Account (401(k), 403(b), 457(b))
//				Inherited Roth IRA
//				SEP IRA
//				SIMPLE IRA
//				Governmental 457
//				403(b) (Pre-Tax)
//				Qualified Plan (Pre-Tax)
						}
						BaseAction.drSelection(page, Funding_Page.surrenderingPlanType,
								rowData.get("Surrendering Plan Type"));
						BaseAction.typeInputField(page, Funding_Page.estimatedAmountofTransfer,
								rowData.get("Estimated Amount of Transfer"));
						BaseAction.drSelection(page, Funding_Page.transferInstructions,
								rowData.get("Transfer Instructions"));
						if (rowData.get("Transfer Instructions").equalsIgnoreCase("Partial - Percentage")) {
							BaseAction.typeInputField(page, Funding_Page.partialPercentage,
									rowData.get("Partial Percentage"));
						} else if (rowData.get("Transfer Instructions").equalsIgnoreCase("Partial - Amount")) {
							BaseAction.typeInputField(page, Funding_Page.partialAmount, rowData.get("Partial Amount"));
						}
						BaseAction.selectByValue(page, Funding_Page.transferTiming, rowData.get("Transfer Timing"));
						if (rowData.get("Check this box").equalsIgnoreCase("Yes")) {
							BaseAction.clickElement(page, Funding_Page.checkThisBox);
						}
					}
					BaseAction.clickElement(page, CommonElements.save);
					BaseAction.waitForNetworkIdle(page);
//					BaseAction.drSelection(page, Funding_Page.initiatedthereplacement,
//							rowData.get("initiated the replacement process"));
				}
			} else {
				throw new AssertionError(
						"Expected payment iteration count is lesser than or equal to 5 but the count is  '"
								+ source.size() + "'");
			}
			BaseAction.isTextPresent(page, Funding_Page.totalCalExpInitialPremiumAmt,
					"$" + rowData.get("total expected initial premium?"));
			if (BaseAction.hasBeforePseudoElement(page, Funding_Page.totalCalExpInitialPremiumTick) == true) {
			} else {
				System.err.println("Tick symbol is not present.....");
			}
			BaseAction.clickElement(page, CommonElements.next);
		} else {
			BaseAction.clickElement(page, Funding_Page.addFunding);
			if (BaseAction.getText(page, CommonElements.pagepopuptext)
					.equalsIgnoreCase(AppConstants.FUNDING_ERROR_MESSAGE)) {
				BaseAction.clickElement(page, CommonElements.proceedAnyways);
			} else {
				System.err.println("Error popup message content mismatch.....");
			}
		}
	}
}
