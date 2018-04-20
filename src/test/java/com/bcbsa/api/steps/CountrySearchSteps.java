package com.bcbsa.api.steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.core.Is.is;

public class CountrySearchSteps {
    private String ISO_CODE_SEARCH = "http://services.groupkt.com/country/get/iso2code/";
    private Response response;

    @Step
    public void useThreeDigitISOCodeWs() {
        ISO_CODE_SEARCH = "http://services.groupkt.com/country/get/iso3code/";
    }

    @Step
    public void searchCountryByCode(String code) {
        response = SerenityRest.when().get(ISO_CODE_SEARCH + code);
    }

    @Step
    public void searchIsExecutedSuccesfully() {
        response.then().statusCode(200);
    }

    @Step
    public void iShouldFindCountry(String country) {
        response.then().body("RestResponse.result.name", is(country));
    }
}
