package org.e2e.connectors.oauthdemobank;

import org.e2e.connectors.oauthdemobank.locators.OAuthDemoBankLocator;
import org.e2e.page.lightbox.LightboxLocator;
import org.e2e.page.oauthwindow.OAuthWindowLocator;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.e2e.config.execution.ExecutionConfig.timeBetweenSteps;

public class OAuthDemoBankFlow implements OAuthDemoBankLocator<OAuthDemoBankFlow>, OAuthWindowLocator, LightboxLocator {

    @Override
    public OAuthDemoBankFlow clickOnGoToDemoBank() {
        byButtonGoToDemoBank.click();
        switchToOAuthWindow();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public OAuthDemoBankFlow fillUserName(String userName) {
        byInputUserName.sendKeys(userName);
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public OAuthDemoBankFlow fillPassword(String password) {
        byInputPassword.sendKeys(password);
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public OAuthDemoBankFlow clickOnLogin() {
        byButtonLogin.click();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public OAuthDemoBankFlow clickOnPayNow() {
        switchTo().window(""); // switching back to the main window
        switchToProperIframe();
        byButtonContinue.click();
        sleep(timeBetweenSteps);
        return this;
    }


    private void switchToProperIframe() {
        switchToParentIFrame();
        switchToPayWithMyBankIframe();
    }

    private void switchToParentIFrame() {
        switchTo().parentFrame();
    }

}
