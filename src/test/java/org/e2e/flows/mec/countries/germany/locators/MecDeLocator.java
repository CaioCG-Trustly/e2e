package org.e2e.flows.mec.countries.germany.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface MecDeLocator<T> {
    SelenideElement byInputIban = $("#lbx-formEcheck-inputiban");

    T fillIBAN(String iban);
}
