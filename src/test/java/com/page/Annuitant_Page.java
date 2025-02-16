package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Annuitant_Page {

	public static String valAnnuitantUSCitizen = "//label//span[contains(normalize-space(text()),'Annuitant a US citizen or Resident Alien?')]//parent::label//parent::div//parent::div[@id]";
	public static Selector annuitantUSCitizen = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Annuitant a US citizen or Resident Alien?')]//parent::label//parent::div//following-sibling::div/label");
	public static Selector relationshipToOwner = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Relationship to Owner']//parent::label//parent::div//following-sibling::div");
	public static Selector residenceStreet1 = new Selector(SelectorType.XPATH,
			"//h3[normalize-space()='Residence Address']//parent::div//label//span[contains(normalize-space(text()),'Street (Line 1)')]//parent::label//parent::div//following-sibling::div//input");

	public static String valAnnuitantSameOwner = "//label//span[contains(normalize-space(text()),'Annuitant the same as the Owner?')]//parent::label//parent::div//parent::div[@id]";
	public static Selector annuitantSameOwner = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Annuitant the same as the Owner?')]//parent::label//parent::div//following-sibling::div/label");
	
	
	public static String isThereJointAnnuitantQuestion = "//label//span[contains(normalize-space(text()),'Is there a Joint Annuitant?')]//parent::label//parent::div//parent::div[@id]";
	public static Selector jointAnnuitantSameOwner = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Joint Annuitant the Owner?')]//parent::label//parent::div//following-sibling::div/label");
	public static Selector isThereJointAnnuitant = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Is there a Joint Annuitant?')]//parent::label//parent::div//following-sibling::div/label");
	
	public static Selector jointAnnuitantSameJointOwner = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Joint Annuitant the Joint Owner?')]//parent::label//parent::div//following-sibling::div/label");
	public static Selector jointAnnuitantOwnerOrJointOwner = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Joint Annuitant the Joint Owner?')]//parent::label//parent::div//following-sibling::div/label");
	
}
