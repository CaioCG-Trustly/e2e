package org.e2e.flows.mec.countries.australia.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface MecAuLocator<T> {
    SelenideElement byInputBSB = $("#lbx-formEcheck-inputbsb");

    T fillBSB(String bsb);
}
