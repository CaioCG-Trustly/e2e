package org.e2e.flows.mec;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface MecCommonLocator<T> {
    // Account Details Screen in MEC transaction
    SelenideElement byInputNameOnAccount = $("#lbx-formEcheck-inputownerName");
    SelenideElement byInputAccountNumber = $("#lbx-formEcheck-inputaccountNumber");
    SelenideElement byInputAccountNumberConfirmation = $("#lbx-formEcheck-inputaccountNumber2");
    SelenideElement byInputDriversLicense = $("#lbx-formEcheck-inputdriverLicenseNumber");
    SelenideElement bySelectAccountType = $("#lbx-formEcheck-selectaccountType");
    SelenideElement bySelectState = $("#lbx-formEcheck-selectdriverLicenseState");
    SelenideElement byButtonSubmitAccountDetails = $("#lbx-formEcheck-submit");

    // Review Screen
    final static SelenideElement byButtonContinue = $("#lbx-echeckConfirmationPage-submit");

    T fillNameOnAccount(String userName);
    T fillAccountNumber(String accountNumber);
    T fillAccountNumberConfirmation(String accountNumber);
    T fillDriversLicense(String driversLicense);
    T selectAccountType(String accountType);
    T selectState(String state);
    T clickOnSubmitAccountDetails();
    T clickOnContinue();

}
