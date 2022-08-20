package org.e2e.flows.westerunion;

import org.e2e.flows.westerunion.locators.DemoBankWesterUnionLocator;
import org.e2e.page.lightbox.LightboxLocator;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.e2e.config.execution.ExecutionConfig.timeBetweenSteps;

public abstract class DemoBankWesterUnionCommon<T extends DemoBankWesterUnionCommon<T>>
    implements DemoBankWesterUnionLocator<DemoBankWesterUnionCommon>, LightboxLocator {

    @Override
    public T fillUserNameWith(String userName) {
        byInputUserName.sendKeys(userName);
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T fillPasswordWith(String password) {
        byInputPassword.sendKeys(password);
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T clickOnContinueButton() {
        byButtonContinue.click();
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T selectAccountWithName(String accountName) {
        switchToParentIFrame();
        $(withText(accountName)).click();
        sleep(timeBetweenSteps);
        return (T) this;
    }

    @Override
    public T clickOnContinueAccountButton() {
        byButtonContinueAccount.click();
        sleep(timeBetweenSteps);
        return (T) this;
    }

    private void switchToParentIFrame() {
        switchTo().parentFrame();
    }
}
