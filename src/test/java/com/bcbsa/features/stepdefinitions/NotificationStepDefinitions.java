package com.bcbsa.features.stepdefinitions;

import com.bcbsa.features.steps.FEPOCAuthAPISteps;
import com.bcbsa.features.steps.NotificationAPISteps;
import com.bcbsa.features.steps.NotificationPageSteps;
import com.bcbsa.helper.Notification;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class NotificationStepDefinitions {

    @Steps
    FEPOCAuthAPISteps authApiSteps;
    @Steps
    NotificationAPISteps notificationAPISteps;
    @Steps
    NotificationPageSteps notificationPageSteps;

    private String eventSubType;
    private Notification notification;

    @Given("^The contractId is '(.*)', memberId is '(.*)'$")
    public void givenTheContractAndMemberId(String contractId, String memberId) {
        notificationAPISteps.setContractId(contractId);
        notificationAPISteps.setMemberId(memberId);
    }

    @And("^The carrierName is '(.*)', numberOfPrescriptions is '(.*)', shipDate is '(.*)', trackingNumber is '(.*)'$")
    public void given_theNumberOfPrescriptions_shipDate_and_trackingNumber(String carrierName, String numberOfPx, String shipDate, String trackingNumber) {
        notificationAPISteps.setCarrierName(carrierName);
        notificationAPISteps.setNumberOfPresciptions(numberOfPx);
        notificationAPISteps.setShipDate(shipDate);
        notificationAPISteps.setTrackingNumber(trackingNumber);
    }

    @When("^I trigger a '(.*)' notification behind the scenes$")
    public void i_trigger_notification(String subType) {
        eventSubType = subType;

        String authToken = authApiSteps.getAuthToken();
        authApiSteps.apiCallExecutedSuccessfully();

        notificationAPISteps.triggerNotification(authToken, subType);
        notificationAPISteps.apiCallExecutedSuccessfully();
    }

    @And("^I login using '(.*)', '(.*)', '(.*)'$")
    public void iLoginAs(String userName, String password, String pin) {
        notificationPageSteps.setUserId(userName);
        notificationPageSteps.setPassword(password);
        notificationPageSteps.setPin(pin);
    }

    @And("^I navigate to the notifications page to view notifications$")
    public void i_navigate_to_notifications_page() {
        notificationPageSteps.navigateToNotificationsPage();
        notificationPageSteps.openNotifications();
    }

    @Then("^I should see the corresponding notification header for this sub type$")
    public void i_see_corresponding_notification() {
        notification = notificationAPISteps.getNotificationForSubType();
        notificationPageSteps.validateNotificationHeaderText(notification.getHeaderText());
    }

    @And("^I should see the corresponding notification body for this sub type$")
    public void i_see_corresponding_notification_body() {
        notificationPageSteps.validateNotificationBodyText(notification.getBodyText());
    }
}
