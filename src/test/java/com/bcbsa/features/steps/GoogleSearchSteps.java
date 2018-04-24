package com.bcbsa.features.steps;

import com.bcbsa.features.pages.com.google.GoogleHomePage;
import com.bcbsa.features.pages.com.google.GoogleSearchResultsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSearchSteps {

    GoogleHomePage homePage;
    GoogleSearchResultsPage searchResultsPage;

    public void opens_google_search_page() {
        homePage.open();
    }

    public void searches_for_keyword(String keyword) {
        homePage.searchFor(keyword);
    }

    public void should_see_title_as(String title) {
        assertThat(searchResultsPage.getTitle()).isEqualTo(title);
    }
}
