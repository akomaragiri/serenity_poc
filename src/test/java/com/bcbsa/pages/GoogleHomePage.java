package com.bcbsa.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

@DefaultUrl("http://www.google.com")
public class GoogleHomePage extends PageObject {

    @FindBy(name = "q")
    WebElement searchBox;

    @FindBy(name = "btnK")
    WebElement searchButton;

    public void searchFor(String keywords) {
        searchBox.sendKeys(keywords);
        searchButton.click();
    }
}
