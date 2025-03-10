package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Started_Product {
	
	public static String termSelectionDropdownQ = "//div[@id='question_40741']";
	public static Selector productDropdown = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Product']//parent::label//parent::div//following-sibling::div/div");
	public static Selector termSelectionDropdown =new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Term Selection']//parent::label//parent::div//following-sibling::div/div");
	public static Selector resubmittedApplicationYNOptions = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()), 'Resubmitted / Replaced application:')]//parent::label//parent::div//following-sibling::div");
	public static Selector policyNumber  = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()), 'Application/Policy Number of the application')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector producerNumber = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Producer Number']//parent::label//parent::div//following-sibling::div/input");
	public static Selector WritingCode = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Writing Code']//parent::label//parent::div//following-sibling::div/input");
	public static Selector agentFirstName = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='First Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector agentMiddleName = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Middle Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector agentLastName = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Last Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector agentEmail = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Email Address']//parent::label//parent::div//following-sibling::div/input");
	public static Selector getSecondaryAgentOptions(int i) {
		 Selector secondaryAgentOptions = new Selector(SelectorType.XPATH, String.format("(//label//span[contains(normalize-space(text()), 'additional Writing Agent')])[%d]//parent::label//parent::div//following-sibling::div/label",i+1));
        return secondaryAgentOptions;
	}
	public static Selector Split = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Split %']//parent::label//parent::div//following-sibling::input");
	public static Selector totalSplit = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Current Split % Total']//parent::label//parent::div/following-sibling::input");
	public static Selector typeOfOwnershipDropdown =new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Type of Ownership']//parent::label//parent::div//following-sibling::div/div");
	public static Selector planTypeDropdown = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Plan Type']//parent::label//parent::div//following-sibling::div/div");
	public static Selector accountDesignation =new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Account Designation']//parent::label//parent::div//following-sibling::div/div");
	public static Selector describeOtherEntity =new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Describe Other Entity']//parent::label//parent::div//following-sibling::div/input");
	public static Selector completedProductTrainingText = new Selector(SelectorType.XPATH, "//label[span[text()=\"Has this agent completed product training?\"]]/ancestor::div[@id][1]/preceding::div[@id][1]/descendant::label/span");
	public static Selector completedProductTraining = new Selector(SelectorType.XPATH, "//label//span[text()='Has this agent completed product training?']/parent::label/parent::div/following-sibling::div/label");
	public static Selector agentEOText = new Selector(SelectorType.XPATH, "//label[span[text()=\"Is the agent's E&O  current and equal to or greater than our minimums ($1m/$2m)?\"]]/ancestor::div[@id][1]/preceding::div[@id][1]/descendant::label/span");
	public static Selector agentEO = new Selector(SelectorType.XPATH, "//label//span[text()=\"Is the agent's E&O  current and equal to or greater than our minimums ($1m/$2m)?\"]/parent::label/parent::div/following-sibling::div//label");
	public static Selector currentAMLTrainingText = new Selector(SelectorType.XPATH, "//label[span[text()=\"Is the agent's AML training current?\"]]/ancestor::div[@id][1]/preceding::div[@id][1]/descendant::label/span");
	public static Selector currentAMLTraining = new Selector(SelectorType.XPATH, "//label//span[text()=\"Is the agent's AML training current?\"]/parent::label/parent::div/following-sibling::div//label");

}
