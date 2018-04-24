package com.bcbsa.features.pages.com.bcbsa;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;

@At(urls = {"#HOST/pilot/login/step-up-auth"})
public class StepUpAuthPage extends PageObject {

    @FindBy(css = "body > div.body-content > div.page-content-holder > div.ng-scope > step-up-auth > div > div > step-up-auth-primer > div > div.primer-explanation > button")
    WebElementFacade continueButton;

    @FindBy(css = "body > div.body-content > div.page-content-holder > div.ng-scope > step-up-auth > div > div > step-up-auth-otp > div > step-up-auth-selection-link > a")
    WebElementFacade verifyYourDeviceAnotherWayLink;

    @FindBy(css = "body > div.body-content > div.page-content-holder > div.ng-scope > step-up-auth > div > div > step-up-auth-selection > div > div > div:nth-child(4) > div:nth-child(2) > div.col-xs-12.col-sm-4.selection > button")
    WebElementFacade useMyPinButton;

    @FindBy(name = "csrPin")
    WebElementFacade userPinTextBox;

    @FindBy(css = "body > div.body-content > div.page-content-holder > div.ng-scope > step-up-auth > div > div > step-up-auth-csr-pin > div > form > button")
    WebElementFacade verifyButton;

    @FindBy(css = "body > div.modal.fade.ng-scope.ng-isolate-scope.in > div > div > step-up-auth-verify-success-modal > div > div > button")
    WebElementFacade continueButtonOnModal;

    public void clickContinueButton() {
        continueButton.waitUntilVisible();
        continueButton.click();
    }

    public void clickVerifyYourDeviceAnotherWayLink() {
        verifyYourDeviceAnotherWayLink.click();
    }

    public void clickUseMyPinButton() {
        useMyPinButton.click();
    }

    public void enterUserPin(String userPin) {
        userPinTextBox.sendKeys(userPin);
    }

    public void clickVerifyButton() {
        verifyButton.click();
    }

    public void clickContinueButtonOnModal() {
        continueButtonOnModal.waitUntilVisible();
        continueButtonOnModal.click();
    }
}
