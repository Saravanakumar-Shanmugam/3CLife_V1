package com.selectors;

public enum SelectorType {

	CSS,
    ID,
    XPATH,
    TEXT,
    NAME,
    CLASS;

    public static boolean isValidSelector(String selector) {
        if (selector == null || selector.trim().isEmpty()) {
            return false;
        }
        try {
            SelectorType.valueOf(selector.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
	
