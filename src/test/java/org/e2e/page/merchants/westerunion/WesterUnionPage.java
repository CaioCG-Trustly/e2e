package org.e2e.page.merchants.westerunion;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.e2e.config.execution.EnvBaseUrl;
import org.e2e.config.execution.ExecutionConfig;
import org.e2e.page.merchants.globex.components.LightBoxComponent;
import org.e2e.page.merchants.westerunion.components.EmbeddedLightBoxComponent;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.testng.Assert.assertTrue;

public class WesterUnionPage {
    static final String localPort = ":7000";

    static final SelenideElement byLinkAddNewBankAccount = $(".linkgo");
    static final SelenideElement byLinkPopulateAndValidate = $("#populate-and-validate");
    static final SelenideElement byDivTransactionSummary = $(".wu-maincontent");
    static final SelenideElement byDivTransactionResultTitle = $(".trx-result-title");
    static final SelenideElement byDivTransactionResultMessage = $(".trx-result-message");

    final String url;
    final LightBoxComponent lightBoxComponent;
    final EmbeddedLightBoxComponent embeddedLightBoxComponent;

    public WesterUnionPage(PaymentType paymentType) {
        this.url = "%s/merchant-demo/wu%s".formatted(handleBaseUrl(), paymentType.suffixUrl);
        lightBoxComponent = new LightBoxComponent();
        embeddedLightBoxComponent = new EmbeddedLightBoxComponent();
    }

    public WesterUnionPage load() {
        open(url);
        return this;
    }

    public WesterUnionPage addNewBankAccountClick() {
        byLinkAddNewBankAccount.click();
        return this;
    }

    public WesterUnionPage waitUntilSuccessMessage() {
        byDivTransactionResultTitle.shouldBe(Condition.visible);
        byDivTransactionResultMessage.shouldBe(Condition.visible);
        return this;
    }

    public LightBoxComponent populateArrowClick() {
        byLinkPopulateAndValidate.click();
        return lightBoxComponent;
    }

    public EmbeddedLightBoxComponent selectBank(String demo_bank) {
        return embeddedLightBoxComponent.selectBank(demo_bank);
    }

    public void assertVerificationStatusIsAuthorized() {
        var summary = byDivTransactionSummary.shouldBe(Condition.visible).getOwnText();
        assertTrue(summary.contains("Verification Status: Authorized"));
        assertTrue(summary.contains("Account Verification: true"));
    }

    public void waitRedirectUrl() {
        var timeToWait = List.of(1000, 2000, 3000, 5000);
        var count = 0;

        while(Objects.equals(this.url, webdriver().driver().url())) {
            sleep(timeToWait.get(count));
            count++;
        }
    }

    public enum PaymentType {
        Verification("/verification/"),
        Retrieval("/retrieval/");

        public final String suffixUrl;

        PaymentType(String suffixUrl) {
            this.suffixUrl = suffixUrl;
        }
    }

    private String handleBaseUrl() {
        var baseUrl = EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment).baseUrl;
        var port = EnvBaseUrl.Local.equals(
            EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment)
        ) ? localPort : "";

        return baseUrl + port;
    }
}
