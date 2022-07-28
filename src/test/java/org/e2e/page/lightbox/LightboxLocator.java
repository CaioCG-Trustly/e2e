package org.e2e.page.lightbox;

import static com.codeborne.selenide.Selenide.switchTo;

public interface LightboxLocator {
    String payWithMyBankIframe = "paywithmybank-iframe";

    default void switchToPayWithMyBankIframe() {
        switchTo().frame(payWithMyBankIframe);
    }
}
