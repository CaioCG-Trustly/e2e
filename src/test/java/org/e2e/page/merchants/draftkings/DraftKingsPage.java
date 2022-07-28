package org.e2e.page.merchants.draftkings;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.e2e.config.execution.EnvBaseUrl;
import org.e2e.config.execution.ExecutionConfig;
import org.e2e.page.merchants.globex.components.LightBoxComponent;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DraftKingsPage {
    static final String localPort = ":7000";

    final String url;
    final LightBoxComponent lightBoxComponent;

    static final SelenideElement byButtonPopulate = $("#populate-button");
    static final SelenideElement byButtonContinue = $("#buy");
    static final SelenideElement byDivTransactionResultTitle = $(".trx-result-title");
    static final SelenideElement byDivTransactionResultMessage = $(".trx-result-message");


    public DraftKingsPage(PaymentType paymentType) {
        this.url = "%s/merchant-demo/draftkings%s".formatted(handleBaseUrl(), paymentType.suffixUrl);
        lightBoxComponent = new LightBoxComponent();
    }

    public DraftKingsPage load() {
        open(url);
        return this;
    }

    public DraftKingsPage clickOnPopulateButton() {
        byButtonPopulate.click();
        return this;
    }

    public LightBoxComponent clickOnContinueButton() {
        byButtonContinue.click();
        return lightBoxComponent;
    }

    public DraftKingsPage waitUntilSuccessMessage() {
        byDivTransactionResultTitle.shouldBe(Condition.visible);
        byDivTransactionResultMessage.shouldBe(Condition.visible);
        return this;
    }

    public enum PaymentType {
        Instant("/instant"),
        Disbursement("/instant/?paymentType=Disbursement");

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
