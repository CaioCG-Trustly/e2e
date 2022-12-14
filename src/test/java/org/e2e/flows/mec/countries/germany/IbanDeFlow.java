package org.e2e.flows.mec.countries.germany;

import com.codeborne.selenide.Condition;
import org.e2e.flows.mec.countries.germany.locators.IbanDeLocator;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.e2e.config.execution.ExecutionConfig.timeBetweenSteps;

public class IbanDeFlow implements IbanDeLocator<IbanDeFlow> {

    @Override
    public IbanDeFlow fillLoginId(String loginId) {
        byInputLoginId.sendKeys(loginId);
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public IbanDeFlow clickOnContinueButton() {
        $(".spinner-img").shouldNotBe(Condition.visible, Duration.ofSeconds(10)); // TODO
        byLinkContinueButton.click();
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public IbanDeFlow fillOneTimeCode() {
        byInputOneTimeCode.sendKeys(getOneTimeCode());
        sleep(timeBetweenSteps);
        return this;
    }

    @Override
    public String getOneTimeCode() {
        return bySpanOneTimeCode.getOwnText();
    }
}
