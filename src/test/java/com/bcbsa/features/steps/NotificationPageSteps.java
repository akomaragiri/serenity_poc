package com.bcbsa.features.steps;

import com.bcbsa.features.pages.com.bcbsa.LandingPage;
import com.bcbsa.features.pages.com.bcbsa.MyBlueLoginPage;
import com.bcbsa.features.pages.com.bcbsa.StepUpAuthPage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

import java.util.List;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getPages;

public class NotificationPageSteps {

    MyBlueLoginPage myBlueLoginPage;
    StepUpAuthPage stepUpAuthPage;
    LandingPage landingPage;

    String userId;
    String password;
    String pin;

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

        myBlueLoginPage.enterUserName(userId);
        myBlueLoginPage.enterPassword(password);
        myBlueLoginPage.submitCredentials();

        stepUpAuthPage.clickContinueButton();
        getStepUpAuthPage().clickVerifyYourDeviceAnotherWayLink();
        getStepUpAuthPage().clickUseMyPinButton();
        getStepUpAuthPage().enterUserPin(pin);
        getStepUpAuthPage().clickVerifyButton();
        getStepUpAuthPage().clickContinueButtonOnModal();
    }

    public void openNotifications() {
        landingPage.clickOnNotNowLinkOnModal();
        getLandingPage().hoverOnNotificationIcon();
    }

    public void validateNotificationHeaderText(String notificationMessage) {
        boolean notificationFound = false;
        for (WebElementFacade webElementFacade : landingPage.getAllElementsContainingNotificationHeaderText()) {
            if (webElementFacade.getText().equals(notificationMessage)) {
                notificationFound = true;
                break;
            }
        }

        Assert.assertTrue(notificationFound);
    }

    public void validateNotificationBodyText(String notificationMessage) {
        boolean notificationFound = false;
        List<WebElementFacade> webElements = landingPage.getAllElementsContainingNotificationBodyText();
        for (WebElementFacade webElementFacade : webElements) {
            if (webElementFacade.getText().trim().equals(notificationMessage.trim())) {
                notificationFound = true;
                break;
            }
        }

        Assert.assertTrue(notificationFound);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
