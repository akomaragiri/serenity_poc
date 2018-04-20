package com.bcbsa.api;

import com.bcbsa.api.steps.CountrySearchSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CountriesSearchTest {
    @Steps
    CountrySearchSteps countrySearchSteps;

    @Test
    public void verifyThatWeCanFindUnitedStatesOfAmericaCountryUsingTheCodeUS() {
        countrySearchSteps.searchCountryByCode("US");
        countrySearchSteps.searchIsExecutedSuccesfully();
        countrySearchSteps.iShouldFindCountry("United States of America");
    }

    @Test
    public void verifyThatWeCanFindIndiaCountryUsingTheCodeIN() {
        countrySearchSteps.searchCountryByCode("IN");
        countrySearchSteps.searchIsExecutedSuccesfully();
        countrySearchSteps.iShouldFindCountry("India");
    }

    @Test
    public void verifyThatWeCanFindBrazilCountryUsingTheCodeBR() {
        countrySearchSteps.searchCountryByCode("BR");
        countrySearchSteps.searchIsExecutedSuccesfully();
        countrySearchSteps.iShouldFindCountry("Brazil");
    }
}
