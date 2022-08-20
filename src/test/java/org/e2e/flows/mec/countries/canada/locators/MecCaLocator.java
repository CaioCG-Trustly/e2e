package org.e2e.flows.mec.countries.canada.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface MecCaLocator<T> {
    SelenideElement byInputTransitNumber = $("#lbx-formEcheck-inputtransitNumber");
    SelenideElement byInputInstitutionNumber = $("#lbx-formEcheck-inputfinancialInstitutionNumber");

    T fillTransitNumber(String transitNumber);
    T fillInstitutionNumber(String institutionNumber);
}
