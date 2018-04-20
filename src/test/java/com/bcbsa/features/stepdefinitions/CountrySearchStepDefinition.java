package com.bcbsa.features.stepdefinitions;

import com.bcbsa.api.steps.CountrySearchSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CountrySearchStepDefinition {

    @Steps
    CountrySearchSteps countrySearchSteps;

    @Given("^I have the webservice URL for two digit ISO codes$")
    public void i_have_the_webservice_URL_for_two_digit_ISO_codes() {
        // nothing to do
    }

    @Given("^I have the webservice URL for three digit ISO codes$")
    public void i_have_the_webservice_URL_for_three_digit_ISO_codes() {
        countrySearchSteps.useThreeDigitISOCodeWs();
    }

    @When("^I search for the country code '(.*)'$")
    public void i_search_for_the_country_code(String countryCode) {
        countrySearchSteps.searchCountryByCode(countryCode);
    }

    @Then("^I should see the response containing '(.*)'$")
    public void i_should_see_the_correct_name_in_response(String countryName) {
        countrySearchSteps.searchIsExecutedSuccesfully();
        countrySearchSteps.iShouldFindCountry(countryName);
    }
}
