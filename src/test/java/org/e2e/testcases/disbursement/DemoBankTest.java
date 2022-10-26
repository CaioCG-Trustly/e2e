package org.e2e.testcases.disbursement;

import org.e2e.config.testrunner.LogListener;
import org.e2e.connectors.demobank.DemoBankDefaultFlow;
import org.e2e.page.merchants.draftkings.DraftKingsPage;
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

    @Test(groups = {"sanity"})
    public void should_create_a_disbursement_transaction_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Disbursement")
            .fillAmountWith("7.00")
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
                    .selectAccountWithName("Random Checking Account")
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
    public void should_create_a_disbursement_transaction_for_draftkings_successfully(ITestContext context) {
        DraftKingsPage draftkings = new DraftKingsPage(DraftKingsPage.PaymentType.Disbursement);
        LightBoxComponent lightBoxComponent = draftkings
            .load()
            .clickOnPopulateButton()
            .clickOnContinueButton();

        lightBoxComponent
            .selectBank("Demo Bank")
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("RandomAccounts")
                    .fillPasswordWith("RandomAccounts")
                    .clickLoginButton()
                    .selectAccountWithName("Random Savings Account")
                    .clickOnSubmitAccount()
                ;
            })
            .waitUntilClose();

        draftkings.waitUntilSuccessMessage(); // It needs to wait, otherwise the url remains the original

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        context.setAttribute(context.getName() + " url", redirectedUrl);
        context.setAttribute(context.getName() + " succeeded", succeeded);
        context.setAttribute(context.getName() + " transactionId", transactionId);

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());
    }
}
