package org.e2e.flows.eox.countries.australia.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface EoxAuLocator<T> {
    SelenideElement byInputLoginId = $(".core_item_content > input[type='text']");
    SelenideElement byLinkContinueButton = $(".button_next");
    SelenideElement bySpanOneTimeCode = $(".message_value");
    SelenideElement byInputOneTimeCode = $("input[type='password']");

    T fillLoginId(String loginId);

    T clickOnContinueButton();

    T fillOneTimeCode();

    String getOneTimeCode();
}
