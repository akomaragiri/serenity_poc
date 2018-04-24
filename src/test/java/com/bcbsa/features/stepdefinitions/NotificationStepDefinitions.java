package com.bcbsa.features.stepdefinitions;

import com.bcbsa.features.steps.FEPOCAuthAPISteps;
import com.bcbsa.features.steps.NotificationAPISteps;
import com.bcbsa.features.steps.NotificationPageSteps;
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

    @Given("^I trigger a '(.*)' notification behind the scenes$")
    public void i_trigger_notification(String subType) {
        eventSubType = subType;

        String authToken = authApiSteps.getAuthToken();
        authApiSteps.apiCallExecutedSuccessfully();

        notificationAPISteps.triggerNotification(authToken, subType);
        notificationAPISteps.apiCallExecutedSuccessfully();
    }

    @When("^I navigate to the notifications page to view notifications$")
    public void i_navigate_to_notifications_page() {
        notificationPageSteps.navigateToNotificationsPage();
        notificationPageSteps.openNotifications();
    }

    @Then("^I should see the corresponding notification for this sub type$")
    public void i_see_corresponding_notification() {
        notificationPageSteps.validateNotificationMessage(eventSubType);
    }
}
