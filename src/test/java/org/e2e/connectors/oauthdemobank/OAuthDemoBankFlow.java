package org.e2e.connectors.oauthdemobank;

import org.e2e.connectors.oauthdemobank.locators.OAuthDemoBankLocator;
import org.e2e.page.lightbox.LightboxLocator;
import org.e2e.page.oauthwindow.OAuthWindowLocator;

import static com.codeborne.selenide.Selenide.switchTo;

public class OAuthDemoBankFlow implements OAuthDemoBankLocator<OAuthDemoBankFlow>, OAuthWindowLocator, LightboxLocator {

    @Override
    public OAuthDemoBankFlow clickOnGoToDemoBank() {
        byButtonGoToDemoBank.click();
        switchToOAuthWindow();
        return this;
    }

    @Override
    public OAuthDemoBankFlow fillUserName(String userName) {
        byInputUserName.sendKeys(userName);
        return this;
    }

    @Override
    public OAuthDemoBankFlow fillPassword(String password) {
        byInputPassword.sendKeys(password);
        return this;
    }

    @Override
    public OAuthDemoBankFlow clickOnLogin() {
        byButtonLogin.click();
        return this;
    }

    @Override
    public OAuthDemoBankFlow clickOnPayNow() {
        switchTo().window(""); // switching back to the main window
        switchToProperIframe();
        byButtonContinue.click();
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
