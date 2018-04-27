package com.bcbsa.features.steps;

import com.bcbsa.helper.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NotificationAPISteps {

    private String notificationAPI = "https://apist.fepblue.org/fepoc/st/events/v1/events";
    private Response response;

    private String contractId;
    private String memberId;
    private String carrierName;
    private String numberOfPresciptions;
    private String shipDate;
    private String trackingNumber;

    private Notification notification;

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
                buildNotification(Notifications.getNotificationHeaderTextForSubType(subType), getNotificationBodyForA1CNotCompleted(), "");
                break;

            case "A1C_TEST_COMPLETED":
                buildA1cCompletedBody(requestSpecBuilder, subType);
                break;

            case "A1C_TEST_NOT_SUBMITTED":
                buildA1CNotSubmittedBody(requestSpecBuilder, subType);
                break;

            case "ORDER_SHPD_CONS":
                buildOrderShippedNotificationBody(requestSpecBuilder, subType);
                buildNotification(Notifications.getNotificationHeaderTextForSubType(subType), buildNotificationBodyForOrderPlaced(), "");
                break;

            default:
                break;
        }

        response = SerenityRest.given().spec(requestSpecBuilder.build()).when().post(notificationAPI);
    }

    private void buildOrderShippedNotificationBody(RequestSpecBuilder requestSpecBuilder, String subType) {
        DataEntryElement element1 = new DataEntryElement("ORDER_NOS", "DXTEST1,DXTEST2");
        DataEntryElement element2 = new DataEntryElement("NUM_OF_RX", getNumberOfPresciptions());
        DataEntryElement element3 = new DataEntryElement("CARRIER_NAME", getCarrierName());
        DataEntryElement element4 = new DataEntryElement("TRACKING_NO", getTrackingNumber());
        DataEntryElement element5 = new DataEntryElement("SHIP_DT", getShipDate());
        DataEntryElement element6 = new DataEntryElement("SIGN_REQ_IND", "True");
        DataEntryElement element7 = new DataEntryElement("EXT_DELIVERY_CHN", "EXT_UNDELIVERED");
        DataEntryElement element8 = new DataEntryElement("MESSAGE_TEXT", "Your order #DXTEST1,DXTEST2 shipped by " + getCarrierName() + " Carrier on " + getShipDate() + ". Your tracking number is " + getTrackingNumber() + ". Please note that your signature will be required.");
        DataEntryElement element9 = new DataEntryElement("EXTERNAL_MESSAGE_ID", "CAREMARK123DDDSR251");

        List<DataEntryElement> dataElementList = new ArrayList<DataEntryElement>();
        dataElementList.add(element1);
        dataElementList.add(element2);
        dataElementList.add(element3);
        dataElementList.add(element4);
        dataElementList.add(element5);
        dataElementList.add(element6);
        dataElementList.add(element7);
        dataElementList.add(element8);
        dataElementList.add(element9);

        AdditionalData additionalData = new AdditionalData();
        additionalData.setAdditionalDataEntry(dataElementList);

        String timeStamp = getCurrentTimeStamp();

        OrderShippedNotificationBody notificationBody = new OrderShippedNotificationBody();
        notificationBody.setEventType("NOTIFICATION_EVENT");
        notificationBody.setEventDateTime(timeStamp);
        notificationBody.setSourceSystem("CAREMARK");
        notificationBody.setMemberID(getMemberId());
        notificationBody.setContractID(getContractId());
        notificationBody.setSubType(subType);
        notificationBody.setAdditionalData(additionalData);

        requestSpecBuilder.setBody(notificationBody);
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
        notificationBody.setMemberID(getMemberId());
        notificationBody.setContractID(getContractId());
        notificationBody.setSubType(subType);
        notificationBody.setExternalID(UUID.randomUUID().toString());

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

    public String getNotificationAPI() {
        return notificationAPI;
    }

    public void setNotificationAPI(String notificationAPI) {
        this.notificationAPI = notificationAPI;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getNumberOfPresciptions() {
        return numberOfPresciptions;
    }

    public void setNumberOfPresciptions(String numberOfPresciptions) {
        this.numberOfPresciptions = numberOfPresciptions;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Notification getNotificationForSubType() {
        return notification;
    }

    private String buildNotificationBodyForOrderPlaced() {
        return " We've combined " + getNumberOfPresciptions() + " of your prescription orders into one package, shipped by " + getCarrierName() + " on " + getShipDate() + ". Your tracking number is " + getTrackingNumber() + ". Please note that your signature will be required.";
    }

    private String getNotificationBodyForA1CNotCompleted() {
        return "After submitting your A1c test result earlier this year, you're well on your way to completing the Diabetes Management Incentive Program. Submit another A1c result from a test performed between July 1 and December 31 this year and you may be eligible to earn an additional $75 on your MyBlue Wellness Card.";
    }

    private void buildNotification(String headerText, String bodyText, String timeLapsed) {
        notification = new Notification(headerText, bodyText, timeLapsed);
    }
}
