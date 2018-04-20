package com.bcbsa.features.stepdefinitions;

import com.bcbsa.features.steps.GoogleSearchSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class SearchByKeywordStepDefinitions {
    @Steps
    GoogleSearchSteps searchSteps;

    @Given("I am on the google page")
    public void openSearchPage() {
        searchSteps.opens_google_search_page();
    }

    @When("I search for the word '(.*)'")
    public void searchByKeyword(String keyword) {
        searchSteps.searches_for_keyword(keyword);
    }

    @Then("I should see the page title as '(.*)'")
    public void searchPageResults(String title) {
        searchSteps.should_see_title_as(title);
    }
}
