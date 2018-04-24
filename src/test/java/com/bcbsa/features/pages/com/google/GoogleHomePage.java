package com.bcbsa.features.pages.com.google;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://www.google.com")
public class GoogleHomePage extends PageObject {

    @FindBy(name = "q")
    WebElementFacade searchBox;

    @FindBy(name = "btnK")
    WebElementFacade searchButton;

    public void searchFor(String keywords) {
        searchBox.sendKeys(keywords);
        searchButton.isEnabled();
        searchButton.click();
    }
}
