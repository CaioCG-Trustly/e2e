package org.e2e.page.oauthwindow;

import static com.codeborne.selenide.Selenide.switchTo;

public interface OAuthWindowLocator {
    String oAuthWindowName = "_FIOAuthWindow";

    default void switchToOAuthWindow() {
        switchTo().window(oAuthWindowName);
    }
}
