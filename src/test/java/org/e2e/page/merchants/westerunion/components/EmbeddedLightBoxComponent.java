package org.e2e.page.merchants.westerunion.components;

import com.codeborne.selenide.SelenideElement;
import org.e2e.connectors.Flow;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class EmbeddedLightBoxComponent {

    static final SelenideElement byIFrameLightBox = $("#paywithmybank-iframe");
    static final SelenideElement byInputSearchBankList = $(".search-bank-input");
    static final SelenideElement byFirstItemOnBankList = $("#contentArea > div:nth-child(1)");
    static final SelenideElement byButtonContinue = $("#select-bank-continue");


    public EmbeddedLightBoxComponent selectBank(String bankName) {
        searchFor(bankName);
        byFirstItemOnBankList.click();
        return this;
    }

    public EmbeddedLightBoxComponent clickOnContinueButton() {
        byButtonContinue.click();
        return this;
    }

    public EmbeddedLightBoxComponent startFlow(Flow executionFlow) {
        executionFlow.execute();
        return this;
    }

    private void searchFor(String bankName) {
        switchToLightBoxIFrame();
        byInputSearchBankList.sendKeys(bankName);
    }

    private void switchToLightBoxIFrame() {
        switchTo().parentFrame();
        switchTo().frame(byIFrameLightBox);
    }
}
