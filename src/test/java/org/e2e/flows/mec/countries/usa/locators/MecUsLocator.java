package org.e2e.flows.mec.countries.usa.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface MecUsLocator<T> {
    SelenideElement byInputRoutingNumber = $("#lbx-formEcheck-inputroutingNumber");

    T fillRoutingNumber(String routingNumber);
}
