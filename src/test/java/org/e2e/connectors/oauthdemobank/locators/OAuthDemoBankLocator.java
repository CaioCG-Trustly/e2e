package org.e2e.connectors.oauthdemobank.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface OAuthDemoBankLocator<T> {
    SelenideElement byButtonGoToDemoBank = $("button[class='sc-bBrNTY iWbUYS']");
    SelenideElement byInputUserName = $("#username");
    SelenideElement byInputPassword = $("#password");
    SelenideElement byButtonLogin = $("input[type='submit']");
    SelenideElement byButtonContinue = $("#lbx-accountList-submit");

    T clickOnGoToDemoBank();
    T fillUserName(String userName);
    T fillPassword(String password);
    T clickOnLogin();
    T clickOnPayNow();
}
