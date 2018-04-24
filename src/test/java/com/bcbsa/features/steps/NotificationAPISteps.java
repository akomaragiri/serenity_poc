package com.bcbsa.features.steps;

import com.bcbsa.helper.NotificationAPIBody;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class NotificationAPISteps {

    private String notificationAPI = "https://apist.fepblue.org/fepoc/st/events/v1/events";
    private Response response;

    @Step
    public void triggerNotification(String authToken, String subType) {

        authToken = "Bearer" + " " + authToken;

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder.addHeader("x-ibm-client-id", "bc2aa626-47ad-47be-91db-76c15917a2cb");
        requestSpecBuilder.addHeader("accept", "application/json");
        requestSpecBuilder.addHeader("authorization", authToken);
        requestSpecBuilder.setContentType("application/json; charset=UTF-8");

        switch (subType) {
            case "A1C_2ND_HALF_TEST_NOT_COMPLTD":
                buildA1cNotCompletedBody(requestSpecBuilder, subType);
                break;

            case "A1C_TEST_COMPLETED":
                buildA1cCompletedBody(requestSpecBuilder, subType);
                break;

            case "A1C_TEST_NOT_SUBMITTED":
                buildA1CNotSubmittedBody(requestSpecBuilder, subType);
                break;

            default:
                break;
        }

        response = SerenityRest.given().spec(requestSpecBuilder.build()).when().post(notificationAPI);
    }

    @Step
    public void apiCallExecutedSuccessfully() {
        response.then().statusCode(200);
    }

    private void buildA1cNotCompletedBody(RequestSpecBuilder requestSpecBuilder, String subType) {
        String timeStamp = getCurrentTimeStamp();

        NotificationAPIBody notificationBody = new NotificationAPIBody();
        notificationBody.setEventType("NOTIFICATION_EVENT");
        notificationBody.setEventDateTime(timeStamp);
        notificationBody.setSourceSystem("CHIPREWARDS");
        notificationBody.setMemberID("13718190");
        notificationBody.setContractID("R60522176");
        notificationBody.setSubType(subType);
        notificationBody.setExternalID(UUID.randomUUID().toString());

//        String notificationBody = "{\n" +
//                "\"eventType\":\"NOTIFICATION_EVENT\",\n" +
//                "\"eventDateTime\": \"" + dateTime + "\",\n" +
//                "\"sourceSystem\":\"CCTI\",\n" +
//                "\"memberID\": \"13718190\",\n" +
//                "\"contractID\":\"R60522176\",\n" +
//                "\"subType\":\"" + subType + "\",\n" +
//                "\"externalID\":\"" + subType + dateTime + "\"" + " \n" +
//                "}\n";

        requestSpecBuilder.setBody(notificationBody);
    }

    private void buildA1cCompletedBody(RequestSpecBuilder requestSpecBuilder, String subType) {
        String timeStamp = getCurrentTimeStamp();

        String notificationBody = "{\n" +
                "\"eventType\": \"NOTIFICATION_EVENT\",\n" +
                "\"eventDateTime\": \"" + timeStamp + "\",\n" +
                "\"sourceSystem\": \"CHIPREWARDS\",\n" +
                "\"contractID\":\"R60522176\",\n" +
                "\"memberID\": \"13718190\",\n" +
                "\"additionalData\": {\n" +
                "\"additionalDataEntry\": [\n" +
                "{\n" +
                "\"key\": \"GUID\",\n" +
                "\"value\": \"" + subType + timeStamp + "\"\n" +
                "},\n" +
                "{\n" +
                "\"key\": \"TEST_NAME\",\n" +
                "\"value\": \"A1c\"\n" +
                "},\n" +
                "{\n" +
                "\"key\": \"TEST_DATE\",\n" +
                "\"value\": \"07/02/2018\"\n" +
                "},\n" +
                "{\n" +
                "\"key\": \"TEST_RESULT\",\n" +
                "\"value\": \"6.00\"\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "}\n";

        requestSpecBuilder.setBody(notificationBody);
    }

    private void buildA1CNotSubmittedBody(RequestSpecBuilder requestSpecBuilder, String subType) {
        String timeStamp = getCurrentTimeStamp();

        String notificationBody = "{\n" +
                "\"eventType\":\"NOTIFICATION_EVENT\",\n" +
                "\"eventDateTime\": \"" + timeStamp + "\",\n" +
                "\"sourceSystem\":\"CCTI\",\n" +
                "\"memberID\": \"13718190\",\n" +
                "\"contractID\":\"R60522176\",\n" +
                "\"subType\":\"" + subType + "\",\n" +
                "\"externalID\":\"" + subType + timeStamp + "\"" + " \n" +
                "}\n";

        requestSpecBuilder.setBody(notificationBody);
    }

    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date());
    }
}
