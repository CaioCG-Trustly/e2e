package org.e2e.flows.mec;

import org.e2e.page.lightbox.LightboxLocator;

import static com.codeborne.selenide.Selenide.switchTo;

public abstract class MecCommon<T extends MecCommon<T>> implements MecCommonLocator<MecCommon>, LightboxLocator {

    public MecCommon() {
        switchToProperIframe();
    }

    @Override
    public T fillNameOnAccount(String userName) {
        byInputNameOnAccount.sendKeys(userName);
        return (T) this;
    }

    @Override
    public T fillAccountNumber(String accountNumber) {
        byInputAccountNumber.sendKeys(accountNumber);
        return (T) this;
    }

    @Override
    public T fillAccountNumberConfirmation(String accountNumber) {
        byInputAccountNumberConfirmation.sendKeys(accountNumber);
        return (T) this;
    }

    @Override
    public T fillDriversLicense(String driversLicense) {
        byInputDriversLicense.sendKeys(driversLicense);
        return (T) this;
    }

    @Override
    public T selectAccountType(String accountType) {
        bySelectAccountType.selectOption(accountType);
        return (T) this;
    }

    @Override
    public T selectState(String state) {
        bySelectState.selectOption(state);
        return (T) this;
    }

    @Override
    public T clickOnSubmitAccountDetails() {
        byButtonSubmitAccountDetails.click();
        return (T) this;
    }

    @Override
    public T clickOnContinue() {
        byButtonContinue.click();
        return (T) this;
    }

    private void switchToProperIframe() {
        switchTo().parentFrame();
        switchToPayWithMyBankIframe();
    }
}
