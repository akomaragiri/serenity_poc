package com.bcbsa.helper;

public enum Status {
    Silver("Silver"),
    Gold("Gold"),
    Platinum("Platinum");

    private String text;

    Status(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
