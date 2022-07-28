package org.e2e.flows.mec.countries.germany.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface IbanDeLocator<T> {
    SelenideElement byInputLoginId = $(".core_item_content > input[type='text']");
    SelenideElement byLinkContinueButton = $(".button_next");
    SelenideElement bySpanOneTimeCode = $(".message_value");
    SelenideElement byInputOneTimeCode = $("input[type='password']");

    T fillLoginId(String loginId);
    T clickOnContinueButton();
    T fillOneTimeCode();
    String getOneTimeCode();

}
