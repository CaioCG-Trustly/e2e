package org.e2e.testcases.deferred;

import org.e2e.config.testrunner.LogListener;
import org.e2e.connectors.demobank.DemoBankDefaultFlow;
import org.e2e.page.merchants.amazon.AmazonPage;
import org.e2e.page.merchants.globex.GlobexPage;
import org.e2e.page.merchants.globex.components.LightBoxComponent;
import org.e2e.testcases.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.e2e.utils.UrlUtils.getQueryParam;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Listeners(LogListener.class)
public class DemoBankTest extends BaseTest {

    @Test(groups = {"sanity", "full"})
    public void should_create_a_deferred_transaction_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Deferred")
            .fillAmountWith("18.00")
            .selectUser("US - John Smith")
            .selectLanguage("English (en)")
            .openLightBoxInBankList();

        lightBoxComponent
            .selectBank("Demo Bank")
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .fillUserNameWith("RandomAccounts")
                    .fillPasswordWith("RandomAccounts")
                    .clickLoginButton()
                    .selectAccountWithName("Random Savings Account")
                    .clickOnSubmitAccount()
                ;
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        context.setAttribute(context.getName() + " url", redirectedUrl);
        context.setAttribute(context.getName() + " succeeded", succeeded);
        context.setAttribute(context.getName() + " transactionId", transactionId);

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());
    }

    @Test(groups = {"sanity"})
    // TODO CRIAR NOVO FLOW POIS ESSE NAO USA OS NOVOS ID #lbx-formAuthenticate-authFields-inputusername
    public void should_create_a_deferred_transaction_for_amazon_successfully(ITestContext context) {
        AmazonPage amazon = new AmazonPage();
        LightBoxComponent lightBoxComponent = amazon
            .load()
            .selectPaymentType("Deferred")
            .clickOnEstablishButton();

        lightBoxComponent
            .selectBank("Demo Bank")
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    //.dismissSlider()
                    .fillUserNameWith("RandomAccounts")
                    .fillPasswordWith("RandomAccounts")
                    .clickLoginButton()
                    .selectAccountWithName("Random Savings Account")
                    .clickOnSubmitAccount()
                ;
            })
            .waitUntilClose();

        amazon.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        context.setAttribute(context.getName() + " url", redirectedUrl);
        context.setAttribute(context.getName() + " succeeded", succeeded);
        context.setAttribute(context.getName() + " transactionId", transactionId);

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        amazon.clickOnVerifyButton();

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    //.dismissSlider()
                    .fillUserNameWith("RandomAccounts")
                    .fillPasswordWith("RandomAccounts")
                    .clickLoginButton();
            });
        amazon.waitRedirectUrl();

        //amazon.assertLabelVerified();
    }
}

