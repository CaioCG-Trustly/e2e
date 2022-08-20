package org.e2e.flows.mec;

import org.e2e.page.lightbox.LightboxLocator;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.e2e.config.execution.ExecutionConfig.timeBetweenSteps;

public abstract class MecCommon<T extends MecCommon<T>> implements MecCommonLocator<MecCommon>, LightboxLocator {

    public MecCommon() {
        switchToProperIframe();
    }

    @Override
    public T fillNameOnAccount(String userName) {
        byInputNameOnAccount.sendKeys(userName);
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T fillAccountNumber(String accountNumber) {
        byInputAccountNumber.sendKeys(accountNumber);
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T fillAccountNumberConfirmation(String accountNumber) {
        byInputAccountNumberConfirmation.sendKeys(accountNumber);
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T fillDriversLicense(String driversLicense) {
        byInputDriversLicense.sendKeys(driversLicense);
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T selectAccountType(String accountType) {
        bySelectAccountType.selectOption(accountType);
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T selectState(String state) {
        bySelectState.selectOption(state);
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T clickOnSubmitAccountDetails() {
        byButtonSubmitAccountDetails.click();
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T clickOnContinue() {
        byButtonContinue.click();
        sleep(timeBetweenSteps);
        return (T) this;
    }

    private void switchToProperIframe() {
        switchTo().parentFrame();
        switchToPayWithMyBankIframe();
    }
}
