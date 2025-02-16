package com.page;

import com.selectors.Selector;
import com.selectors.SelectorType;

public class LoginPage {

	public static Selector emailInput = new Selector(SelectorType.ID, "email");
	public static Selector passwordInput = new Selector(SelectorType.ID, "password");
	public static Selector loginInput = new Selector(SelectorType.XPATH, "//button[text()='Login']");
}
