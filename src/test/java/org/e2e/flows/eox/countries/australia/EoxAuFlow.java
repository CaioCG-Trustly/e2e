package org.e2e.flows.eox.countries.australia;


import com.codeborne.selenide.Condition;
import org.e2e.flows.eox.countries.australia.locators.EoxAuLocator;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.e2e.config.execution.ExecutionConfig.timeBetweenSteps;

public class EoxAuFlow implements EoxAuLocator<EoxAuFlow> {
    @Override
    public EoxAuFlow fillLoginId(String loginId) {
        byInputLoginId.sendKeys(loginId);
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public EoxAuFlow clickOnContinueButton() {
        $(".spinner-img").shouldNotBe(Condition.visible, Duration.ofSeconds(10)); // TODO
        byLinkContinueButton.click();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public EoxAuFlow fillOneTimeCode() {
        byInputOneTimeCode.sendKeys(getOneTimeCode());
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public String getOneTimeCode() {
        return bySpanOneTimeCode.getOwnText();
    }

    public EoxAuFlow selectAccountWithName(String accountName) {
        $(withText(accountName)).click();
        sleep(timeBetweenSteps);
        return this;
    }

    private void switchToParentIFrame() {
        switchTo().parentFrame();
    }
}
