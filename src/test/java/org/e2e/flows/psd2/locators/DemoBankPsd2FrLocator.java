package org.e2e.flows.psd2.locators;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface DemoBankPsd2FrLocator<T> {
    SelenideElement byButtonGoToDemoBank = $(By.xpath("//*[@id=\"ExternalRedirect\"]/div[1]/button"));;
    SelenideElement byButtonLogin = $(".ei_btn_body");
    SelenideElement byButtonAccept = $("#I0\\:btnConfirmSubmission\\:labelsubmit");
    SelenideElement byButtonContinue = $("#lbx-accountList-submit");

    T clickOnGoToDemoBank();
    T clickOnLogin();
    T clickOnAccept();
    T clickOnContinue();
}
