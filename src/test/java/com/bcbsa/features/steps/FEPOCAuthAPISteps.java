package com.bcbsa.features.steps;

import com.bcbsa.helper.FEPOCAuthAPIBody;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class FEPOCAuthAPISteps {

    private String authAPI = "https://apist.fepblue.org/fepoc/st/oauth2/v1/token";
    private Response response;

    @Step
    public String getAuthToken() {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder.addHeader("x-ibm-client-id", "bc2aa626-47ad-47be-91db-76c15917a2cb");
        requestSpecBuilder.addHeader("grant_type", "client_credentials");
        requestSpecBuilder.addHeader("x-ibm-client-secret", "hD3nI6fF4qN4iQ4vM7uO5rG2vQ5xH2xG3gS2bU4gY8qW4lA4lY");
        requestSpecBuilder.addHeader("accept", "application/json");
        requestSpecBuilder.setContentType("application/json; charset=UTF-8");

        FEPOCAuthAPIBody body = new FEPOCAuthAPIBody();
        body.setUserType("Vendor");
        body.setUserName("bluestester01");

        //requestSpecBuilder.setBody("{\"userType\":\"Vendor\", \"userName\":\"bluestester01\"}");
        requestSpecBuilder.setBody(body);

        response = SerenityRest.given().spec(requestSpecBuilder.build()).when().post(authAPI);
        return response.getBody().jsonPath().get("access_token");
    }

    @Step
    public void apiCallExecutedSuccessfully() {
        response.then().statusCode(200);
    }

}
