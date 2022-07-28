package org.e2e.flows.westerunion;

import com.codeborne.selenide.SelenideElement;
import org.e2e.flows.westerunion.locators.DemoBankWesterUnionLocator;
import org.e2e.page.lightbox.LightboxLocator;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class DemoBankWesterUnionFlow extends DemoBankWesterUnionCommon<DemoBankWesterUnionFlow> {

    @Override
    public DemoBankWesterUnionFlow fillUserNameWith(String userName) {
        switchToPayWithMyBankIframe();
        byInputUserName.sendKeys(userName);
        return this;
    }
}
