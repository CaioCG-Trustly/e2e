package org.e2e.connectors.demobank;

import com.codeborne.selenide.Condition;
import org.e2e.connectors.demobank.locators.DemoBankDefaultLocator;
import org.e2e.page.lightbox.LightboxLocator;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.e2e.config.execution.ExecutionConfig.timeBetweenSteps;

public class DemoBankDefaultFlow implements DemoBankDefaultLocator<DemoBankDefaultFlow>, LightboxLocator {

    @Override
    public DemoBankDefaultFlow fillUserNameWith(String userName) {
        switchToAuthenticationIFrame();
        byInputUserName.sendKeys(userName);
        //executeJavaScript("arguments[0].style.border='3px solid red'", byInputUserName);
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public DemoBankDefaultFlow fillPasswordWith(String password) {
        byInputPassword.sendKeys(password);
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public DemoBankDefaultFlow clickLoginButton() {
        byButtonLogin.click();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public DemoBankDefaultFlow clickOnGoToDemoBank() {
        byButtonGoToDemoBank.click();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public DemoBankDefaultFlow selectAccountWithName(String accountName) {
        switchToParentIFrame();
        $(withText(accountName)).click();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public DemoBankDefaultFlow clickOnSubmitAccount() {
//        switchTo().frame(payWithMyBankIframe);
//        var a = DomUtils.getCurrentIframeName();
        byButtonSubmitAccount.shouldBe(Condition.enabled);
        byButtonSubmitAccount.click();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public DemoBankDefaultFlow dismissSlider() {
        byLoginPage
            .shouldBe(Condition.enabled, Duration.ofSeconds(5))
            .shouldBe(Condition.visible, Duration.ofSeconds(5));

        byWelcomeSlider
            .shouldBe(Condition.enabled, Duration.ofSeconds(5))
            .shouldBe(Condition.visible, Duration.ofSeconds(5));

        byButtonSlider.shouldBe(Condition.visible);
        byButtonSlider.click();
        byButtonSlider.shouldNotBe(Condition.visible);

        sleep(timeBetweenSteps);
        return this;
    }

    private void switchToAuthenticationIFrame() {
        try {
            switchTo().frame(authenticationIframe);
        } catch (Error e) {
            switchTo().frame(payWithMyBankIframe);
            switchTo().frame(authenticationIframe);
        }
    }

    private void switchToParentIFrame() {
        switchTo().parentFrame();
    }
}
