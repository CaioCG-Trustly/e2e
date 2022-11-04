package org.e2e.testcases;

import com.codeborne.selenide.Configuration;
import org.e2e.config.execution.ExecutionConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.closeWindow;

public class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void setup() {
        Configuration.browser = ExecutionConfig.targetBrowser;
        Configuration.browserSize = ExecutionConfig.browserSize;
        Configuration.headless = ExecutionConfig.headlessMode;
        Configuration.timeout = ExecutionConfig.defaultTimeout;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // TODO Check if it is better to quit the browser instead of trying to keep local storage clean
        // I think there is a problem with cache when using developer option after many tests, this is
        // the best I could do locally. It is not perfect because it is failing occasionally
//        try {
//            while (!localStorage().isEmpty()) {
//                executeJavaScript("window.localStorage.clear(); window.sessionStorage.clear()");
//                sleep(200);
//            }
//        } catch (JavascriptException e) {
//            // do nothing
//        }
        closeWindow();
//        closeWebDriver();
    }
}
