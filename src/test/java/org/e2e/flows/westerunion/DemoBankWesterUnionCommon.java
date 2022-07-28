package org.e2e.flows.westerunion;

import org.e2e.flows.mec.MecCommon;
import org.e2e.flows.westerunion.locators.DemoBankWesterUnionLocator;
import org.e2e.page.lightbox.LightboxLocator;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public abstract class DemoBankWesterUnionCommon<T extends DemoBankWesterUnionCommon<T>>
    implements DemoBankWesterUnionLocator<DemoBankWesterUnionCommon> , LightboxLocator {

    @Override
    public T fillUserNameWith(String userName) {
        byInputUserName.sendKeys(userName);
        return (T) this;
    }

    @Override
    public T fillPasswordWith(String password) {
        byInputPassword.sendKeys(password);
        return (T) this;
    }

    @Override
    public T clickOnContinueButton() {
        byButtonContinue.click();
        return (T) this;
    }

    @Override
    public T selectAccountWithName(String accountName) {
        switchToParentIFrame();
        $(withText(accountName)).click();
        return (T) this;
    }

    @Override
    public T clickOnContinueAccountButton() {
        byButtonContinueAccount.click();
        return (T) this;
    }

    private void switchToParentIFrame() {
        switchTo().parentFrame();
    }
}
