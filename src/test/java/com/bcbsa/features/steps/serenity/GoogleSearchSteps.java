package com.bcbsa.features.steps.serenity;

import com.bcbsa.pages.GoogleHomePage;
import com.bcbsa.pages.GoogleSearchResultsPage;

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
