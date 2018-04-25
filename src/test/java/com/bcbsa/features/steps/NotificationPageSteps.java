package com.bcbsa.features.steps;

import com.bcbsa.features.pages.com.bcbsa.LandingPage;
import com.bcbsa.features.pages.com.bcbsa.MyBlueLoginPage;
import com.bcbsa.features.pages.com.bcbsa.StepUpAuthPage;
import com.bcbsa.helper.Notifications;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getPages;

public class NotificationPageSteps {

    MyBlueLoginPage myBlueLoginPage;
    StepUpAuthPage stepUpAuthPage;
    LandingPage landingPage;

    public MyBlueLoginPage getMyBlueLoginPage() {
        return getPages().currentPageAt(MyBlueLoginPage.class);
    }

    public StepUpAuthPage getStepUpAuthPage() {
        return getPages().currentPageAt(StepUpAuthPage.class);
    }

    public LandingPage getLandingPage() {
        return getPages().currentPageAt(LandingPage.class);
    }

    public void navigateToNotificationsPage() {
        myBlueLoginPage.open();

        myBlueLoginPage.enterUserName("ASPEN");
        myBlueLoginPage.enterPassword("Littestr#4");
        myBlueLoginPage.submitCredentials();

        stepUpAuthPage.clickContinueButton();
        getStepUpAuthPage().clickVerifyYourDeviceAnotherWayLink();
        getStepUpAuthPage().clickUseMyPinButton();
        getStepUpAuthPage().enterUserPin("5622");
        getStepUpAuthPage().clickVerifyButton();
        getStepUpAuthPage().clickContinueButtonOnModal();
    }

    public void openNotifications() {
        landingPage.clickOnNotNowLinkOnModal();
        getLandingPage().hoverOnNotificationIcon();
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
