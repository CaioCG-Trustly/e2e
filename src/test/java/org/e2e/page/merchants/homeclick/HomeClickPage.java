package org.e2e.page.merchants.homeclick;

import com.codeborne.selenide.SelenideElement;
import org.e2e.config.execution.EnvBaseUrl;
import org.e2e.config.execution.ExecutionConfig;
import org.e2e.page.merchants.globex.components.LightBoxComponent;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.webdriver;

public class HomeClickPage {
    static final String localPort = ":7000";

    static final SelenideElement byLinkSubmitOrder = $("div[class='bb_checkout'] > a");

    final String url;
    final LightBoxComponent lightBoxComponent;

    public HomeClickPage(PaymentType paymentType) {
        this.url = "%s/merchant-demo/homeclick%s".formatted(handleBaseUrl(), paymentType.suffixUrl);
        lightBoxComponent = new LightBoxComponent();
    }

    public HomeClickPage load() {
        open(url);
        return this;
    }

    public LightBoxComponent clickOnSubmitOrder() {
        byLinkSubmitOrder.click();
        return lightBoxComponent;
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
        Instant("/instant/"),
        Deferred("/instant/?paymentType=Deferred"),
        Recurring("/instant/?paymentType=Recurring");

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
