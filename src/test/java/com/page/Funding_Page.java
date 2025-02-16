package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Funding_Page {
	
	public static String totalexpectedPnitialPremiumVal = "//label//span[contains(normalize-space(text()),'What is the total expected initial premium?')]//parent::label//parent::div//parent::div[@id]";

	public static Selector totalexpectedPnitialPremium =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'What is the total expected initial premium?')]//parent::label/parent::div//following-sibling::input");
	public static Selector totalCalculatedExpectedInitialPremium =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Total Calculated Expected Initial Premium')]//parent::label/parent::div//following-sibling::input");

	public static Selector totalCalExpInitialPremiumAmt =new Selector(SelectorType.XPATH, "//div//h3[contains(normalize-space(text()), 'Total Calculated Expected Initial Premium')]/parent::div/following-sibling::div//span[contains(@class, 'bold ')]");
	public static Selector totalCalExpInitialPremiumTick =new Selector(SelectorType.XPATH, "//div//h3[contains(normalize-space(text()), 'Total Calculated Expected Initial Premium')]/parent::div/following-sibling::div//span[contains(@class, 'ext-success')]");

	public static Selector addFunding =new Selector(SelectorType.XPATH, "//button[text()='Add Funding']");

	public static Selector paymentUniqueID =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Payment Unique ID')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector fundingSource =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Funding Source')]//parent::label//parent::div//following-sibling::div");
	public static Selector informedSurrenderingCompany =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'agent informed the Surrendering Company')]//parent::label//parent::div//following-sibling::div");
	public static Selector paymentForm =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Payment Form')]//parent::label//parent::div//following-sibling::div");

	public static Selector currentYearContribution =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Current Year Contribution')]//parent::label//parent::div//following-sibling::input");
	public static Selector priorYearContribution =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Prior Year Contribution')]//parent::label//parent::div//following-sibling::input");
	public static Selector selectAccountOwner =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Select Account Owner')]//parent::label//parent::div//following-sibling::div");
	public static Selector accountOwnerName =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Account Owner Name')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector accountOwnerEmail =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Account Owner Email')]//parent::label//parent::div//following-sibling::div/input");

	public static Selector accountType =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Account Type')]//parent::label//parent::div//following-sibling::div");
	public static Selector bankRouting =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Bank Routing')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector bankName =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Bank Name')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector accountNumber =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Account Number')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector amount =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Amount')]//parent::label//parent::div//following-sibling::input");

	public static Selector contractNumber =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Surrendering Account / Policy / Contract Number')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector surrenderingCompanyName =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Surrendering Company Name')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector companySearch =new Selector(SelectorType.XPATH, "//button[contains(normalize-space(),'Company Search')]");
	public static Selector universalCarrierID =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Universal Carrier ID')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector universalLocationID =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'1035YP UniversalLocationID')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector CompanyAddressLine1 =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Company Address Line 1')]//parent::label//parent::div//following-sibling::div//div//input");
	public static Selector CompanyAddressLine2 =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Company Address Line 2')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector City =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'City')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector State =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'State')]//parent::label//parent::div//following-sibling::div");
	public static Selector zipCode =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Zip Code')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector phoneNumber =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Phone Number')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector extension =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Extension')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector faxNumber =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Fax Number')]//parent::label//parent::div//following-sibling::div/input");

	public static Selector initiatedthereplacement =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'initiated the replacement process')]//parent::label//parent::div//following-sibling::div");
	
	
	public static Selector surrenderingProductType =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Surrendering Product Type')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector surrenderingPlanType =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Surrendering Plan Type')]//parent::label//parent::div//following-sibling::div");
	public static Selector estimatedAmountofTransfer =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Estimated Amount of Transfer')]//parent::label//parent::div//following-sibling::input");
	public static Selector transferInstructions =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Transfer Instructions')]//parent::label//parent::div//following-sibling::div");
	public static Selector transferTiming =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Transfer Timing')]//parent::label//parent::div//following-sibling::div");
	public static Selector partialAmount =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Partial Amount')]//parent::label//parent::div//following-sibling::input");
	public static Selector partialPercentage =new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(),'Partial Percentage')]//parent::label//parent::div//following-sibling::div//input");
	public static Selector checkThisBox =new Selector(SelectorType.XPATH, "//span[contains(text(),'Check this box')]//parent::label");
	public static Selector ShrProcessingLocations =new Selector(SelectorType.XPATH, "//div[@class='list-header']//following-sibling::div//label");
	public static Selector selectCompany =new Selector(SelectorType.XPATH, "//button[text()='SELECT COMPANY']");


	
	
}
