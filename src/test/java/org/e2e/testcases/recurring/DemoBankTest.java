package org.e2e.testcases.recurring;

import org.e2e.config.testrunner.LogListener;
import org.e2e.connectors.demobank.DemoBankDefaultFlow;
import org.e2e.connectors.oauthdemobank.OAuthDemoBankFlow;
import org.e2e.page.merchants.globex.GlobexPage;
import org.e2e.page.merchants.globex.components.LightBoxComponent;
import org.e2e.page.merchants.homeclick.HomeClickPage;
import org.e2e.testcases.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.e2e.page.merchants.homeclick.HomeClickPage.PaymentType.Recurring;
import static org.e2e.utils.UrlUtils.getQueryParam;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Listeners(LogListener.class)
public class DemoBankTest extends BaseTest {

    @Test(groups = {"sanity"})
    public void should_create_a_recurring_transaction_for_gb_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Recurring")
            .fillAmountWith("7.00")
            .selectUser("GB - Freddie Mercury")
            .selectLanguage("English (en)")
            .openLightBoxThroughLogInToMyBankButton();

        lightBoxComponent
            .selectBank("Demo Bank")
            .startFlow(() -> {
                var flow = new OAuthDemoBankFlow();

                flow
                    .clickOnGoToDemoBank()
                    .fillUserName("RandomAccounts")
                    .fillPassword("RandomAccounts")
                    .clickOnLogin()
                    .clickOnPayNow();
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
    public void should_create_a_recurring_transaction_selecting_spanish_language_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Recurring")
            .fillAmountWith("7.00")
            .selectUser("US - John Smith")
            .selectLanguage("Spanish (es) - Spain (es)")
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
    public void should_create_a_recurring_transaction_using_homeclick_successfully(ITestContext context) {
        HomeClickPage homeClick = new HomeClickPage(Recurring);
        LightBoxComponent lightBoxComponent = homeClick
            .load()
            .clickOnSubmitOrder();

        lightBoxComponent
            .selectBank("Demo Bank")
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("RandomAccounts")
                    .fillPasswordWith("RandomAccounts")
                    .clickLoginButton()
                    .selectAccountWithName("Random Checking Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        homeClick.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        //var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        context.setAttribute(context.getName() + " url", redirectedUrl);
        //context.setAttribute(context.getName() + " succeeded", succeeded);
        context.setAttribute(context.getName() + " transactionId", transactionId);

        //assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());
    }

}
