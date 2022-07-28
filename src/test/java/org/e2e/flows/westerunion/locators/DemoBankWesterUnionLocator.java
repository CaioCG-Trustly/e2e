package org.e2e.flows.westerunion.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface DemoBankWesterUnionLocator<T> {
    SelenideElement byInputUserName = $("#svp-username");
    SelenideElement byInputPassword = $("#svp-password");
    SelenideElement byButtonContinue = $("#continue-login");
    SelenideElement byButtonContinueAccount = $("#continue-account");

    T fillUserNameWith(String userName);
    T fillPasswordWith(String password);
    T clickOnContinueButton();
    T selectAccountWithName(String accountName);
    T clickOnContinueAccountButton();
}
