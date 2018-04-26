package com.bcbsa.features.pages.com.bcbsa;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

@At(urls = {"#HOST/pilot/landingpage"})
public class LandingPage extends PageObject {

    @FindBy(css = "body > div.modal.fade.ng-scope.ng-isolate-scope.in > div > div > sms-collection-modal > div > div.sms-collection-footer > a.not-now")
    WebElementFacade notNowLinkOnModal;

    @FindBy(css = "#Notification > a > span")
    WebElementFacade notificationIcon;

    @FindBy(id = "NotificationsDropdown", timeoutInSeconds = "15")
    WebElementFacade notificationsDropDown;

    public void clickOnNotNowLinkOnModal() {
        withTimeoutOf(15, TimeUnit.SECONDS).waitForPresenceOf(By.cssSelector("body > div.modal.fade.ng-scope.ng-isolate-scope.in > div > div > sms-collection-modal > div > div.sms-collection-footer > a.not-now"));
        notNowLinkOnModal.click();
    }

    public void hoverOnNotificationIcon() {
        notificationIcon.waitUntilVisible();
        Actions actions = new Actions(getDriver());
        actions.moveToElement(notificationIcon).build().perform();
    }

    public List<WebElementFacade> getAllElementsContainingNotificationText() {
        notificationsDropDown.waitUntilVisible();
        return notificationsDropDown.thenFindAll(By.className("ntf-headline"));
    }
}
