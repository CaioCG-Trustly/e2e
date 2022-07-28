package org.e2e.flows.mec.countries.greatbritish.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface MecGbLocator<T> {
    SelenideElement byInputSortCode = $("#lbx-formEcheck-inputsortCode");

    T fillSortCode(String sortCode);
}
