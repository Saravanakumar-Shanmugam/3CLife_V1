package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Suitability_Page {

	// Section 1: Additional Information

	public static String valCorporationorEntityUSBased = "//div[@id='question_41634']";
	public static Selector activeDutyPersonnel = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'active duty personnel?')]//parent::label/parent::div//following-sibling::div/label");

	// Section 2: Financial Resources and Objectives

	public static Selector incomeSources = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'Identify your income sources')]//parent::label/parent::div//following-sibling::div//label");
	public static Selector reverseMortgageOrHomEquityLoan = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'reverse mortgage or home equity loan')]//parent::label/parent::div//following-sibling::div//label");
	public static Selector financialResources = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'financial resource(s)')]//parent::label/parent::div//following-sibling::div//label");
	public static Selector financialObjectives = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'financial objectives ')]//parent::label/parent::div//following-sibling::div//label");
	public static Selector intendedUseAnyRiders = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'intended use of any riders')]//parent::label/parent::div//following-sibling::div//label");
	public static Selector contractFeatures = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'contract features')]//parent::label/parent::div//following-sibling::div//label");

	public static Selector selectedGuaranteeInterestPeriod = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'selected guarantee interest period')]//parent::label/parent::div//following-sibling::div//label");
	public static Selector experienceLevelFinancialProduct = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'experience level with financial product')]//parent::label/parent::div//following-sibling::div//label");

	public static Selector contractualBenefitsAssociated = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'contractual benefits associated with any of the funding source(')]//parent::label/parent::div//following-sibling::div//label");
	public static Selector penaltiesAssociated = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'penalties associated with any of the funding source(')]//parent::label/parent::div//following-sibling::div//label");
	public static Selector financialResourcesOther = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'financial resource')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'Other (please specify)')]] //parent::div//following-sibling::div/input)[1]");
	public static Selector financialobjectivesOther = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'financial objectives')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'Other (please specify)')]] //parent::div//following-sibling::div/input)[1]");

	public static Selector incomeSourcesOther = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'income sources')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'Other (please specify)')]] //parent::div//following-sibling::div/input)[1]");

	public static Selector amountReceivedFromSeverance = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'monthly amount received from Severance')]]//parent::div//following-sibling::input");
	public static Selector severanceExpectedToContinue = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'monthly amount received from Severance')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'How long is that income expected to continue')]] //parent::div//following-sibling::div/input)[1]");
	public static Selector amountReceivedFromDisability = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'monthly amount received from Disability')]]//parent::div//following-sibling::input");
	public static Selector disabilityExpectedToContinue = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'monthly amount received from Disability')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'How long is that income expected to continue')]] //parent::div//following-sibling::div/input)[1]");
	public static Selector amountReceivedFromChild = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'monthly amount received from Child')]]//parent::div//following-sibling::input");
	public static Selector childExpectedToContinue = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'monthly amount received from Child')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'How long is that income expected to continue')]] //parent::div//following-sibling::div/input)[1]");
	public static Selector amountReceivedFromAlimony = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'monthly amount received from Alimony')]]//parent::div//following-sibling::input");
	public static Selector alimonyExpectedToContinue = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'monthly amount received from Alimony')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'How long is that income expected to continue')]] //parent::div//following-sibling::div/input)[1]");
	public static Selector amountReceivedFromUnemployment = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'monthly amount received from Unemployment')]]//parent::div//following-sibling::input");
	public static Selector unemploymentExpectedToContinue = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'monthly amount received from Unemployment')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), ' expected to continue')]] //parent::div//following-sibling::div/input)[1]");
	public static Selector familyMember = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'Family Member/Non-Family Member')]]//parent::label//parent::div//following-sibling::div/input");

	public static Selector generalRiskTolerance = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'general risk tolerance')]//parent::label/parent::div//following-sibling::div//label");
	public static Selector aggressive = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'Aggressive')]//parent::label/parent::div//following-sibling::div//input");

	public static Selector aggregateAmountPenalty = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(),'aggregate amount of penalty')]//parent::label/parent::div//following-sibling::input");

	// Section 3: Financial Profile and Status

	public static Selector approximateHouseholdNetWorth = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'Approximate Household Net Worth')]]//parent::div//following-sibling::input");
	public static Selector grossHouseholdAnnualIncome = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'Gross Household Annual Income')]]//parent::div//following-sibling::input");
	public static Selector approximateTotalHouseholdDebt = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'Approximate Total Household Debt')]]//parent::div//following-sibling::input");
	public static Selector verageLevelMonthlyMxpenses = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'What is your average level of monthly expenses?')]]//parent::div//following-sibling::input");
	public static Selector householdLiquidAssets = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'Household Liquid Assets')]]//parent::div//following-sibling::input");
	public static Selector householdNonLiquidAssets = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'Household Non-Liquid Assets')]]//parent::div//following-sibling::input");
	public static Selector expectChangeFutureIncome = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'expect a change in your future income')]]//parent::div//following-sibling::div/label");
	public static Selector expectChangeFutureIncomeYes = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'expect a change in your future income')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'If yes, please explain:')]] //parent::div//following-sibling::div/input)[1]");
	public static Selector amountPaidForAnnuity = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'amount being paid for this annuity')]]//parent::div//following-sibling::div/label");

	// Replacements

	public static String valReplacingAnnuity = "//div[@id='question_40690']";
	public static Selector replacingAnnuity = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'replacing an annuity')]]//parent::div//following-sibling::div/label");
	public static Selector annuityReplacedleast60Months = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'annuity being replaced for at least 60 months')]]//parent::div//following-sibling::div/label");
	public static Selector exchangedPast60Months = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'exchanged within the past 60 months')]]//parent::div//following-sibling::div/label");
	public static Selector replacedOrExchangedRncomeRide = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'replaced or exchanged have an income ride')]]//parent::div//following-sibling::div/label");
	public static Selector twoTieredAnnuityIncomePayout = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'two-tiered annuity that provides and income payout')]]//parent::div//following-sibling::div/label");
	public static Selector guaranteedFixedInterestRate = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'guaranteed fixed interest rate')]]//parent::div//following-sibling::input");
	public static Selector deathBenefitRide = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'death benefit ride')]]//parent::div//following-sibling::div/label");
	public static Selector deathBenefitRideYes = new Selector(SelectorType.XPATH,
			"(//label[span[contains(normalize-space(text()), 'death benefit rider')]] //ancestor::div[3] //following-sibling::div//label[span[contains(normalize-space(text()), 'If ”Yes,”')]] //parent::div//following-sibling::input)[1]");
	public static Selector withdrawaChargePeriod = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'withdrawal charge period')]]//parent::div//following-sibling::div/label");
	public static Selector withdrawaCharge = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'withdrawal charge')]]//parent::div//following-sibling::div/input");
	public static Selector withdrawaValue = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'withdrawal value')]]//parent::div//following-sibling::input");
	public static Selector fullyConsideredSurrende = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'fully considered the surrender')]]//parent::div//following-sibling::div/label");
	public static Selector runPreAssessmentButton = new Selector(SelectorType.XPATH,
			"//button[normalize-space()='RUN PRE-ASSESSMENT']");
	public static Selector suitabilityReviewLabel = new Selector(SelectorType.XPATH,
			"//span[contains(normalize-space(),'This application has cleared preliminary suitability review.')]");

	
	// Owner + Agent Statements

	// Owner Statement


	public static Selector selectAsMany = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'Select as many of the following that apply')]]//parent::div//following-sibling::div//div/label");
	public static Selector personOrRemote = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'person or are they remote')]]//parent::div//following-sibling::div/label");

	// Agent Statement

	public static Selector submittingSupplemental = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'submitting supplemental')]]//parent::div//following-sibling::div/label");
	public static Selector typeOfProductQLabel = new Selector(SelectorType.XPATH, "//div[@id=\"question_45659\"]//p");
	public static Selector typeOfProductALabel = new Selector(SelectorType.XPATH, "//div[@id=\"question_45660\"]//p");
	public static Selector offerProducts = new Selector(SelectorType.XPATH, "//label[span[contains(normalize-space(text()), 'I offer the following products:')]]//parent::div//following-sibling::div//label");
	public static Selector licensedAuthorizedProvideAdvice =new Selector(SelectorType.XPATH, "//label[span[contains(normalize-space(text()), ' licensed and authorized to provide advice')]]//parent::div//following-sibling::div//label");
	public static Selector authorizedToSell = new Selector(SelectorType.XPATH, "//label[span[contains(normalize-space(text()), ' authorized to sell:')]]//parent::div//following-sibling::div");
	
	public static Selector paidQLabel = new Selector(SelectorType.XPATH, "//div[@id=\"question_45665\"]//p");
	public static Selector paidALabel = new Selector(SelectorType.XPATH, "//div[@id=\"question_45666\"]//p");
	public static Selector paidCashCompensation = new Selector(SelectorType.XPATH, "//label[span[contains(normalize-space(text()), 'paid cash compensation')]]//parent::div//following-sibling::div//label");
	
	public static Selector describeOtherSources = new Selector(SelectorType.XPATH, "//div[@id='question_45668']//div/input");
	public static Selector otherFees = new Selector(SelectorType.XPATH, "//label[span[contains(normalize-space(text()), 'Other Fees')]]//parent::div//following-sibling::div/input");
	public static Selector other = new Selector(SelectorType.XPATH, "//label//span[normalize-space()='Other (please explain)']//parent::label//parent::div//following-sibling::div//input");

	public static Selector specify = new Selector(SelectorType.XPATH, "//div[@id='question_45664']//input");
	
	
	// Agent Disclosures and Acknowledgments

	public static Selector relationshipWithApplicant = new Selector(SelectorType.XPATH, "//div[@id='question_46166']//div/div[contains(@class,'custom-select__control')]");
	public static Selector LevelOfAcquaintance  = new Selector(SelectorType.XPATH, "//div[@id='question_46202']//div/div[contains(@class,'custom-select__control')]");
	public static Selector Years  = new Selector(SelectorType.XPATH, "//div[@id='question_46200']//div/input");
	public static Selector Months  = new Selector(SelectorType.XPATH, "//div[@id='question_46201']//div/input");
	public static Selector existingInsuranceOrAnnuities  = new Selector(SelectorType.XPATH, "//div[@id='question_41624']//div[contains(@class,'custom-radio')]//label");
	public static Selector changeOrReplaceExistingInsurance  = new Selector(SelectorType.XPATH, "//div[@id='question_41632']//div[contains(@class,'custom-radio')]//label");
	public static Selector gaveApplicantCopy  = new Selector(SelectorType.XPATH, "//div[@id='question_41626']//div[contains(@class,'custom-radio')]//label");
	public static Selector haveMadeReasonableEffort  = new Selector(SelectorType.XPATH, "//div[@id='question_41627']//div[contains(@class,'custom-radio')]//label");
	public static Selector productRecommendationWellGrounded  = new Selector(SelectorType.XPATH, "//div[@id='question_41628']//div[contains(@class,'custom-radio')]//label");
	public static Selector accuratelyRecorded  = new Selector(SelectorType.XPATH, "//div[@id='question_41629']//div[contains(@class,'custom-radio')]//label");
	public static Selector applicationCompletion  = new Selector(SelectorType.XPATH, "//div[@id='question_41630']//div[contains(@class,'custom-radio')]//label");
	public static Selector reasonForRecommendingProduct  = new Selector(SelectorType.XPATH, "//span[normalize-space()='Reason for Recommending the Product']//parent::label//parent::div//following-sibling::div//label");

	
}
