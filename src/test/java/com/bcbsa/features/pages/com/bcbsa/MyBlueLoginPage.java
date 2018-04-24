package com.bcbsa.features.pages.com.bcbsa;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://stage.fepblue.org:8443/pilot/login")
public class MyBlueLoginPage extends PageObject {

    @FindBy(id = "LoginUsername")
    WebElementFacade userNameTextBox;

    @FindBy(id = "LoginPassword")
    WebElementFacade passwordTextBox;

    @FindBy(id = "login")
    WebElementFacade loginButton;

    public void enterUserName(String userName) {
        userNameTextBox.sendKeys(userName);
    }

    public void enterPassword(String password) {
        passwordTextBox.sendKeys(password);
    }

    public void submitCredentials() {
        loginButton.click();
    }
}
