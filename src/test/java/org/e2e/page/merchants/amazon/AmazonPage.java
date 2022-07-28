package org.e2e.page.merchants.amazon;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.e2e.config.execution.EnvBaseUrl;
import org.e2e.config.execution.ExecutionConfig;
import org.e2e.page.merchants.globex.components.LightBoxComponent;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.webdriver;

public class AmazonPage {
    static final String localPort = ":7000";

    static final SelenideElement byButtonEstablish = $("#retrieval-button");
    static final SelenideElement byButtonVerify = $("#verification-button");
    static final SelenideElement bySelectPaymentType = $("#paymentType");
    static final SelenideElement byLabelVerified = $(withTagAndText("label", "Verified"));

    final String url;
    final LightBoxComponent lightBoxComponent;

    public AmazonPage() {
        this.url = "%s/merchant-demo/amazon/retrieval/".formatted(handleBaseUrl());
        lightBoxComponent = new LightBoxComponent();
    }

    public AmazonPage load() {
        open(url);
        return this;
    }

    public AmazonPage selectPaymentType(String paymentType) {
        bySelectPaymentType.selectOption(paymentType);
        return this;
    }

    public LightBoxComponent clickOnEstablishButton() {
        byButtonEstablish.click();
        return lightBoxComponent;
    }

    public LightBoxComponent clickOnVerifyButton() {
        byButtonVerify.click();
        return lightBoxComponent;
    }



    private String handleBaseUrl() {
        var baseUrl = EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment).baseUrl;
        var port = EnvBaseUrl.Local.equals(
            EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment)
        ) ? localPort : "";

        return baseUrl + port;
    }

    public void assertLabelVerified() {
        byLabelVerified.should(Condition.exist);
    }

    public void waitRedirectUrl() {
        var timeToWait = List.of(1000, 2000, 3000, 5000);
        var count = 0;

        while(Objects.equals(this.url, webdriver().driver().url())) {
            sleep(timeToWait.get(count));
            count++;
        }
    }
}
