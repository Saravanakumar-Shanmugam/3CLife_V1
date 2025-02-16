package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class CreateApplicationPopup {
	
	 // Input fields
    public static Selector agentNumberInput = new Selector(SelectorType.ID, "agentNumber");

    // Buttons
    public static Selector createApplicationButton = new Selector(SelectorType.XPATH, "//button[text()='Create new application']");
    public static Selector startApplicationButton = new Selector(SelectorType.XPATH, "//button[text()='Start Application']");
    
    public static Selector stateDropdown =new Selector(SelectorType.XPATH, "//label[text()='State']/following-sibling::div");
    
}
