package org.e2e.utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class DomUtils {
    public static String getCurrentIframeName() {
        return executeJavaScript("return self.name");
    }
}
