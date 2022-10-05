package org.e2e.connectors.demobank.locators;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface DemoBankDefaultLocator<T> {
    String authenticationIframe = "lbx-iframeAuthenticate";
    SelenideElement byInputUserName = $("#lbx-formAuthenticate-authFields-inputusername");
    SelenideElement byInputPassword = $("#lbx-formAuthenticate-authFields-inputpassword");
    SelenideElement byButtonLogin = $("#lbx-formLogin-submit");
    SelenideElement bySelectAccount = $("#lbx-accountList-select");
    SelenideElement byButtonSubmitAccount = $("#lbx-accountList-submit");
    SelenideElement byButtonSlider = $("#slider-button");
    SelenideElement byButtonGoToDemoBank = $(By.xpath("//*[@id=\"ExternalRedirect\"]/div[1]/button"));
    SelenideElement byLoginPage = $(".login-page");
    SelenideElement byWelcomeSlider = $(".welcome-slider--enabled");


    T fillUserNameWith(String userName);
    T fillPasswordWith(String password);
    T clickLoginButton();
    T clickOnGoToDemoBank();
    T selectAccountWithName(String accountName);
    T clickOnSubmitAccount();
    T dismissSlider();
}
