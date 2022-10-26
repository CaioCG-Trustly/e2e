package org.e2e.testcases.instant;

import org.e2e.config.testrunner.LogListener;
import org.e2e.connectors.demobank.DemoBankDefaultFlow;
import org.e2e.flows.mec.countries.canada.MecCaFlow;
import org.e2e.flows.mec.countries.usa.MecUsFlow;
import org.e2e.page.merchants.globex.GlobexPage;
import org.e2e.page.merchants.globex.components.LightBoxComponent;
import org.e2e.page.merchants.homeclick.HomeClickPage;
import org.e2e.testcases.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.e2e.page.merchants.homeclick.HomeClickPage.PaymentType.Instant;
import static org.e2e.utils.UrlUtils.getQueryParam;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Listeners(LogListener.class)
public class DemoBankTest extends BaseTest {

    @Test(groups = {"sanity"})
    public void should_create_an_instant_transaction_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Instant")
            .fillAmountWith("15.00")
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
                    .clickOnSubmitAccount();
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
    public void should_create_an_instant_mec_transaction_for_us_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith")
            .selectLanguage("English (en)")
            .enableDeveloperOptionsWith((options) -> options
                .selectPaymentProvider("Manual")
                .selectManualPaymentProviderSubtype("MED/MEC"))
            .openLightBoxInBankList();

        lightBoxComponent
            .startFlow(() -> {
                var flow = new MecUsFlow();

                flow
                    .fillNameOnAccount("Caio Gava")
                    .fillRoutingNumber("124003116")
                    .fillAccountNumber("12345678")
                    .fillAccountNumberConfirmation("12345678")
                    .selectAccountType("Personal - Checking")
                    .clickOnSubmitAccountDetails()
                    .clickOnContinue();
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
    public void should_create_an_instant_mec_transaction_for_ca_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
            .selectUser("CA - Alanis Morissette")
            .selectLanguage("English (en)")
            .enableDeveloperOptionsWith((options) -> options
                .selectPaymentProvider("Manual")
                .selectManualPaymentProviderSubtype("MED/MEC"))
            .openLightBoxInBankList();

        lightBoxComponent
            .startFlow(() -> {
                var flow = new MecCaFlow();

                flow
                    .fillNameOnAccount("Caio Gava")
                    .fillTransitNumber("98392")
                    .fillInstitutionNumber("002")
                    .fillAccountNumber("12345678")
                    .fillAccountNumberConfirmation("12345678")
                    .selectAccountType("Personal - Checking")
                    .clickOnSubmitAccountDetails()
                    .clickOnContinue();
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
    public void should_create_an_instant_transaction_using_homeclick_successfully(ITestContext context) {
        HomeClickPage homeClick = new HomeClickPage(Instant);
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

    @Test(groups = {"sanity"})
    public void should_create_an_instant_mec_transaction_using_homeclick_successfully(ITestContext context) {
        HomeClickPage homeClick = new HomeClickPage(Instant);
        LightBoxComponent lightBoxComponent = homeClick
            .load()
            .clickOnSubmitOrder();

        lightBoxComponent
            .searchFor("Inexistent")
            .clickOnEnterAccountManually()
            .startFlow(() -> {
                var flow = new MecUsFlow();

                flow
                    .fillNameOnAccount("Caio Gava")
                    .fillRoutingNumber("124003116")
                    .fillAccountNumber("12345678")
                    .fillAccountNumberConfirmation("12345678")
                    .selectAccountType("Personal - Checking")
                    //.fillDriversLicense("124ABC") // TODO EM INT ESSES CAMPOS NAO EXIBIDOS, EM UAT SAO
                    //.selectState("CA")
                    .clickOnSubmitAccountDetails()
                    .clickOnContinue();
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

