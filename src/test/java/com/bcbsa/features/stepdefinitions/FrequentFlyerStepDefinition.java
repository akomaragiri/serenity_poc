package com.bcbsa.features.stepdefinitions;

import com.bcbsa.features.steps.FrequentFlyerSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class FrequentFlyerStepDefinition {

    @Steps
    FrequentFlyerSteps frequentFlyerSteps;

    @Given("^I have a frequent flyer account with (\\d+) points$")
    public void i_have_a_frequent_flyer_account_with_points(int existingBalance) {
        frequentFlyerSteps.a_traveller_has_a_frequent_flyer_account_with_balance(existingBalance);
    }

    @When("^I fly (\\d+) kilometers$")
    public void i_fly_kilometers(int distance) {
        frequentFlyerSteps.the_traveller_flies(distance);
    }

    @Then("^I should have (\\d+) points$")
    public void i_should_have_points(int expectedBalance) {
        frequentFlyerSteps.traveller_should_have_a_balance_of(expectedBalance);
    }
}
