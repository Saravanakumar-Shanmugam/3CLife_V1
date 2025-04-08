package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Annuitant_Page {

	public static String valAnnuitantUSCitizen = "//span[contains(normalize-space(text()),'citizen or resident alien?')]//parent::label//parent::div//parent::div[@id]";
	public static Selector annuitantUSCitizen = new Selector(SelectorType.XPATH,
			"//span[contains(normalize-space(text()),'citizen or resident alien?')]//parent::label//parent::div//following-sibling::div/label");
	public static Selector relationshipToOwner = new Selector(SelectorType.XPATH,
			"//label//span[normalize-space(text())='Relationship to Owner']//parent::label//parent::div//following-sibling::div");
	public static Selector residenceStreet1 = new Selector(SelectorType.XPATH,
			"//h3[normalize-space()='Residence Address']//parent::div//label//span[contains(normalize-space(text()),'Street (Line 1)')]//parent::label//parent::div//following-sibling::div//input");

	public static String valAnnuitantSameOwner = "//label//span[contains(normalize-space(text()),'Annuitant the same as the Owner?')]//parent::label//parent::div//parent::div[@id]";
	public static Selector annuitantSameOwner = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Annuitant the same as the Owner?')]//parent::label//parent::div//following-sibling::div/label");
	
	
	public static String isThereJointAnnuitantQuestion = "//label//span[contains(normalize-space(text()),'Is there a Joint Annuitant?')]//parent::label//parent::div//parent::div[@id]";
	public static Selector jointAnnuitantSameOwner = new Selector(SelectorType.XPATH,
			"//label/span[contains(normalize-space(text()),'Joint Annuitant, also the Owner')]/parent::label/parent::div/following-sibling::div/label");
	public static Selector isThereJointAnnuitant = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Is there a Joint Annuitant?')]//parent::label//parent::div//following-sibling::div/label");

	public static Selector annuitantOwnerOrJointOwner = new Selector(SelectorType.XPATH,
			"//label//span[contains(normalize-space(text()),'Is the Annuitant the Owner or the Joint Owner?')]//parent::label//parent::div//following-sibling::div/label");


}
