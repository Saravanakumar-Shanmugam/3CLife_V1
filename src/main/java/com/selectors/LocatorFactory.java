package com.selectors;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LocatorFactory {

 
    public static Locator getLocator(Page page, Selector selector) {
        if (page == null) {
            throw new IllegalArgumentException("Page cannot be null");
        }
        if (selector == null || selector.getType() == null || selector.getValue() == null || selector.getValue().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid selector: Selector type and value must be non-null and non-empty");
        }

        switch (selector.getType()) {
            case CSS:
                return page.locator(selector.getValue());
            case XPATH:
                return page.locator(selector.getValue());
            case ID:
                return page.locator("#" + selector.getValue());
            case TEXT:
                return page.locator("text=" + selector.getValue());
            case NAME:
                return page.locator("[name='" + selector.getValue() + "']");
            case CLASS:
                return page.locator("." + selector.getValue());
            default:
                throw new IllegalArgumentException("Unsupported selector type: " + selector.getType());
        }
    }
}