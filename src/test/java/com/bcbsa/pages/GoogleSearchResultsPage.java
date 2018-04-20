package com.bcbsa.pages;

import net.serenitybdd.core.pages.PageObject;

public class GoogleSearchResultsPage extends PageObject {

    public String getTitle() {
        return getDriver().getTitle();
    }
}
