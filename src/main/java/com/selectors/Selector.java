package com.selectors;


public class Selector {
    private final SelectorType type;
    private final String value;

    public Selector(SelectorType type, String value) {
        if (type == null || value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Selector type and value cannot be null or empty");
        }
        this.type = type;
        this.value = value;
    }

    public SelectorType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}