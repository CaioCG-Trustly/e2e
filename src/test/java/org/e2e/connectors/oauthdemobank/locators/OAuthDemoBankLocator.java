package org.e2e.connectors.oauthdemobank.locators;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface OAuthDemoBankLocator<T> {
    SelenideElement byButtonGoToDemoBank = $(By.xpath("//*[@id=\"ExternalRedirect\"]/div[1]/button"));;
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
