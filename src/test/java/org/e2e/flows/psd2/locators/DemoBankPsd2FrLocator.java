package org.e2e.flows.psd2.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface DemoBankPsd2FrLocator<T> {
    SelenideElement byButtonGoToDemoBank = $("button[class='sc-bBrNTY iWbUYS']");
    SelenideElement byButtonLogin = $(".ei_btn_body");
    SelenideElement byButtonAccept = $("#I0\\:btnConfirmSubmission\\:labelsubmit");
    SelenideElement byButtonContinue = $("#lbx-accountList-submit");

    T clickOnGoToDemoBank();
    T clickOnLogin();
    T clickOnAccept();
    T clickOnContinue();
}
