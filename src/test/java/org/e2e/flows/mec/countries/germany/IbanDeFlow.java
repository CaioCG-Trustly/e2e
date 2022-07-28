package org.e2e.flows.mec.countries.germany;

import com.codeborne.selenide.Condition;
import org.e2e.flows.mec.countries.germany.locators.IbanDeLocator;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class IbanDeFlow implements IbanDeLocator<IbanDeFlow>
{

    @Override
    public IbanDeFlow fillLoginId(String loginId) {
        byInputLoginId.sendKeys(loginId);
        return this;
    }

    @Override
    public IbanDeFlow clickOnContinueButton() {
        $(".spinner-img").shouldNotBe(Condition.visible, Duration.ofSeconds(10)); // TODO
        byLinkContinueButton.click();
        return this;
    }

    @Override
    public IbanDeFlow fillOneTimeCode() {
        byInputOneTimeCode.sendKeys(getOneTimeCode());
        return this;
    }

    @Override
    public String getOneTimeCode() {
        return bySpanOneTimeCode.getOwnText();
    }
}
