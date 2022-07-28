package org.e2e.page.merchants.att;

import org.e2e.config.execution.EnvBaseUrl;
import org.e2e.config.execution.ExecutionConfig;
import org.e2e.page.merchants.globex.components.LightBoxComponent;
import org.e2e.page.merchants.globex.components.WidgetComponent;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.webdriver;

public class AttPage {
    static final String localPort = ":7000";

    final String url;
    final WidgetComponent widgetComponent;

    public AttPage() {
        this.url = "%s/merchant-demo/att/retrieval/".formatted(handleBaseUrl());
        widgetComponent = new WidgetComponent();
    }

    public AttPage load() {
        open(url);
        return this;
    }

    public LightBoxComponent openLightBoxInBankList() {
        return widgetComponent
            .clickAllOtherBanks();
    }

    private String handleBaseUrl() {
        var baseUrl = EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment).baseUrl;
        var port = EnvBaseUrl.Local.equals(
            EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment)
        ) ? localPort : "";

        return baseUrl + port;
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
