package org.e2e.flows.psd2;

import org.e2e.flows.psd2.locators.DemoBankPsd2FrLocator;
import org.e2e.page.lightbox.LightboxLocator;
import org.e2e.page.oauthwindow.OAuthWindowLocator;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.e2e.config.execution.ExecutionConfig.timeBetweenSteps;

public class DemoBankPsd2Flow
    implements DemoBankPsd2FrLocator<DemoBankPsd2Flow>, LightboxLocator, OAuthWindowLocator {

    public DemoBankPsd2Flow() {
        switchToProperIframe();
    }

    @Override
    public DemoBankPsd2Flow clickOnGoToDemoBank() {
        byButtonGoToDemoBank.click();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public DemoBankPsd2Flow clickOnLogin() {
        switchToOAuthWindow();
        byButtonLogin.click();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public DemoBankPsd2Flow clickOnAccept() {
        byButtonAccept.click();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public DemoBankPsd2Flow clickOnContinue() {
        switchTo().window(""); // switching back to the main window
        switchToProperIframe();
        byButtonContinue.click();
        sleep(timeBetweenSteps);
        return this;
    }

    private void switchToProperIframe() {
        switchTo().parentFrame();
        switchToPayWithMyBankIframe();
    }

}
