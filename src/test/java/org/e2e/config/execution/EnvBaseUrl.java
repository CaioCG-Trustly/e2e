package org.e2e.config.execution;

public enum EnvBaseUrl {
    Local("http://192.168.15.33"),
    Int("https://int.paywithmybank.com"),
    BrowserStack("https://" + ExecutionConfig.browserstackUser + ":" + ExecutionConfig.browserstackAccessKey + "@hub-cloud.browserstack.com/wd/hub")
    ;

    public final String baseUrl;

    EnvBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
