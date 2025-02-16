package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Started_Product {
	
	public static Selector productDropdown = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Product']//parent::label//parent::div//following-sibling::div/div");
	public static Selector termSelectionDropdown =new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Term Selection']//parent::label//parent::div//following-sibling::div/div");
	public static Selector resubmittedApplicationYNOptions = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()), 'Resubmitted / Replaced application:')]//parent::label//parent::div//following-sibling::div");
	public static Selector policyNumber  = new Selector(SelectorType.XPATH, "//label//span[contains(normalize-space(text()), 'Application/Policy Number of the application')]//parent::label//parent::div//following-sibling::div/input");
	public static Selector producerNumber = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Producer Number']//parent::label//parent::div//following-sibling::div/input");
	public static Selector WritingCode = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Writing Code']//parent::label//parent::div//following-sibling::div/input");
	public static Selector agentFirstName = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='First Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector agentLastName = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Last Name']//parent::label//parent::div//following-sibling::div/input");
	public static Selector agentEmail = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Email Address']//parent::label//parent::div//following-sibling::div/input");
	public static Selector getSecondaryAgentOptions(int i) {
		 Selector secondaryAgentOptions = new Selector(SelectorType.XPATH, String.format("(//label//span[contains(normalize-space(text()), 'additional Writing Agent')])[%d]//parent::label//parent::div//following-sibling::div/label",i+1));
        return secondaryAgentOptions;
	}
	public static Selector Split = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Split %']//parent::label//parent::div//following-sibling::div/input");
	public static Selector totalSplit = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Current Split % Total']//parent::label//parent::div//following-sibling::div/input");
	public static Selector typeOfOwnershipDropdown =new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Type of Ownership']//parent::label//parent::div//following-sibling::div/div");
	public static Selector planTypeDropdown = new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Plan Type']//parent::label//parent::div//following-sibling::div/div");
	public static Selector accountDesignation =new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Account Designation']//parent::label//parent::div//following-sibling::div/div");
	public static Selector describeOtherEntity =new Selector(SelectorType.XPATH, "//label//span[normalize-space(text())='Describe Other Entity']//parent::label//parent::div//following-sibling::div/input");
	
	public static Selector writingCode = new Selector(SelectorType.XPATH, "//tr/td[count(//th[text()='Writing Code']/preceding-sibling::th)+1]//input");
	public static Selector Name = new Selector(SelectorType.XPATH, "//tr/td[count(//th[text()='Name']/preceding-sibling::th)+1]//input");
	public static Selector Email = new Selector(SelectorType.XPATH, "//tr/td[count(//th[text()='Email']/preceding-sibling::th)+1]//input");
//	public static Selector Split = new Selector(SelectorType.XPATH, "//tr/td[count(//th[text()='Split%']/preceding-sibling::th)+1]//input");
	public static Selector Action = new Selector(SelectorType.XPATH, "//tr/td[count(//th[text()='Action']/preceding-sibling::th)+1]//div/span");

	public static Selector addPerson = new Selector(SelectorType.XPATH, "//button[normalize-space()='Add Person']");

}
