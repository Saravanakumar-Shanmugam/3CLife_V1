package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class Consents_Remarks_Page {	
	// Remarks

	public static Selector remarks = new Selector(SelectorType.XPATH,
			"//label[span[contains(normalize-space(text()), 'remarks ')]]//parent::div//following-sibling::div//textarea");
	
}
