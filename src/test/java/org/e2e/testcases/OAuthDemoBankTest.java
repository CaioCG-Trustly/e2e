package org.e2e.testcases;

import org.e2e.config.testrunner.LogListener;
import org.e2e.connectors.oauthdemobank.OAuthDemoBankFlow;
import org.e2e.page.merchants.globex.GlobexPage;
import org.e2e.page.merchants.globex.components.LightBoxComponent;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.e2e.utils.UrlUtils.getQueryParam;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Listeners(LogListener.class)
public class OAuthDemoBankTest extends BaseTest {

    @Test(groups = { "sanity" })
    public void should_create_an_instant_transaction_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Instant")
            .fillAmountWith("11.00")
            .selectUser("US - John Smith")
            .selectLanguage("English (en)")
            .openLightBoxInBankList();

        lightBoxComponent
            .selectBank("oAuth Demo Bank")
            .startFlow(() -> {
                var flow = new OAuthDemoBankFlow();

                flow
                    .clickOnGoToDemoBank()
                    .fillUserName("RandomAccounts")
                    .fillPassword("RandomAccounts")
                    .clickOnLogin()
                    .clickOnPayNow()
                ;
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        context.setAttribute("url", redirectedUrl);
        context.setAttribute("succeeded", succeeded);
        context.setAttribute("transactionId", transactionId);

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());
    }
}

