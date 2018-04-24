package com.bcbsa.features.steps;

import com.bcbsa.features.pages.com.bcbsa.LandingPage;
import com.bcbsa.features.pages.com.bcbsa.MyBlueLoginPage;
import com.bcbsa.features.pages.com.bcbsa.StepUpAuthPage;
import com.bcbsa.helper.Notifications;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

public class NotificationPageSteps {

    MyBlueLoginPage myBlueLoginPage;
    StepUpAuthPage stepUpAuthPage;
    LandingPage landingPage;

    public void navigateToNotificationsPage() {
        myBlueLoginPage.open();
        myBlueLoginPage.enterUserName("Sean3");
        myBlueLoginPage.enterPassword("Sittest1!");
        myBlueLoginPage.submitCredentials();

        stepUpAuthPage.clickContinueButton();
        stepUpAuthPage.clickVerifyYourDeviceAnotherWayLink();
        stepUpAuthPage.clickUseMyPinButton();
        stepUpAuthPage.enterUserPin("6128");
        stepUpAuthPage.clickVerifyButton();
        stepUpAuthPage.clickContinueButtonOnModal();
    }

    public void openNotifications() {
        landingPage.clickOnNotNowLinkOnModal();
        landingPage.hoverOnNotificationIcon();
    }

    public void validateNotificationMessage(String subType) {
        String expectedNotificationMessage = Notifications.getNotificationMessageForSubType(subType);

        boolean notificationFound = false;
        for (WebElementFacade webElementFacade : landingPage.getAllElementsContainingNotificationText()) {
            if (webElementFacade.getText().equals(expectedNotificationMessage)) {
                notificationFound = true;
                break;
            }
        }

        Assert.assertTrue(notificationFound);
    }
}
