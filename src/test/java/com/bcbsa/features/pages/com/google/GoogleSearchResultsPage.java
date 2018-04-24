package com.bcbsa.features.pages.com.google;

import net.serenitybdd.core.pages.PageObject;

public class GoogleSearchResultsPage extends PageObject {

    public String getTitle() {
        return getDriver().getTitle();
    }
}
