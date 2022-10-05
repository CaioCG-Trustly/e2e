package org.e2e.testcases;

import org.e2e.config.testrunner.LogListener;
import org.e2e.connectors.demobank.DemoBankDefaultFlow;
import org.e2e.connectors.oauthdemobank.OAuthDemoBankFlow;
import org.e2e.flows.eox.countries.australia.EoxAuFlow;
import org.e2e.flows.mec.countries.australia.MecAuFlow;
import org.e2e.flows.mec.countries.canada.MecCaFlow;
import org.e2e.flows.mec.countries.germany.IbanDeFlow;
import org.e2e.flows.mec.countries.germany.MecDeFlow;
import org.e2e.flows.mec.countries.greatbritish.MecGbFlow;
import org.e2e.flows.mec.countries.usa.MecUsFlow;
import org.e2e.flows.psd2.DemoBankPsd2Flow;
import org.e2e.flows.westerunion.DemoBankWesterUnionEmbeddedFlow;
import org.e2e.flows.westerunion.DemoBankWesterUnionFlow;
import org.e2e.page.merchants.amazon.AmazonPage;
import org.e2e.page.merchants.att.AttPage;
import org.e2e.page.merchants.draftkings.DraftKingsPage;
import org.e2e.page.merchants.globex.GlobexPage;
import org.e2e.page.merchants.globex.components.LightBoxComponent;
import org.e2e.page.merchants.homeclick.HomeClickPage;
import org.e2e.page.merchants.westerunion.WesterUnionPage;
import org.e2e.page.merchants.westerunion.components.EmbeddedLightBoxComponent;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.e2e.page.merchants.homeclick.HomeClickPage.PaymentType.Deferred;
import static org.e2e.page.merchants.homeclick.HomeClickPage.PaymentType.Instant;
import static org.e2e.page.merchants.homeclick.HomeClickPage.PaymentType.Recurring;
import static org.e2e.utils.UrlUtils.getQueryParam;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Listeners(LogListener.class)
public class DemoBankIntTest extends BaseTest {

    @Test(groups = { "sanity" })
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

    @Test(groups = { "sanity", "full" })
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

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_transaction_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
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

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_transaction_for_ca_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
            .selectUser("CA - Alanis Morissette")
            .selectLanguage("English (en)")
            .openLightBoxInBankList();

        lightBoxComponent
            .selectBank("Demo Bank of Canada")
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

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_transaction_for_de_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
            .selectUser("DE - Franz Beckenbauer")
            .selectLanguage("English (en)")
            .openLightBoxInBankList();

        lightBoxComponent
            .fillIBAN("DE73500105177492489759") // TODO na pratica, nao está relamente selecionando esse IBAN, apenas ativa a lista de resultados, e seleciona o primeiro
            .clickOnContinueForIban()
            .clickOnContinueForIban()
            .startFlow(() -> {
                var flow = new IbanDeFlow();

                flow
                    .fillLoginId("12345678")
                    .clickOnContinueButton()
                    .fillOneTimeCode()
                    .clickOnContinueButton()
                    .clickOnContinueButton()
                ;
            })
            .waitUntilClose();

        globex.waitRedirectUrl(); // TODO talvez colocar isso dentro de alguma rotina de validacao pra aplicar a todos

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        context.setAttribute(context.getName() + " url", redirectedUrl);
        context.setAttribute(context.getName() + " succeeded", succeeded);
        context.setAttribute(context.getName() + " transactionId", transactionId);

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());
    }

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_transaction_for_au_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
            .selectUser("AU - Daniel Johns")
            .selectLanguage("English (en)")
            .openLightBoxInBankList();

        lightBoxComponent
            .selectBankByLogo("Westpac Banking Corporation")
            .clickOnContinueForIban()
            .startFlow(() -> {
                var flow = new EoxAuFlow();

                flow
                    .clickOnContinueButton()
                    .fillLoginId("12345678")
                    .clickOnContinueButton()
                    .fillOneTimeCode()
                    .clickOnContinueButton()
                    .selectAccountWithName("Savings account")
                    .clickOnContinueButton()
                ;
            })
            .waitUntilClose();

        globex.waitRedirectUrl(); // TODO talvez colocar isso dentro de alguma rotina de validacao pra aplicar a todos

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        context.setAttribute(context.getName() + " url", redirectedUrl);
        context.setAttribute(context.getName() + " succeeded", succeeded);
        context.setAttribute(context.getName() + " transactionId", transactionId);

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());
    }

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_transaction_for_gb_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
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

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_mcd_transaction_for_de_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
            .selectUser("DE - Franz Beckenbauer")
            .selectLanguage("English (en)")
            .enableDeveloperOptionsWith((options) -> options
                .selectPaymentProvider("Manual")
                .selectManualPaymentProviderSubtype("MCD"))
            .openLightBoxInBankList();

        lightBoxComponent
            .fillFirstName("Caio Cesar")
            .fillLastName("Gava")
            .fillIbanMcd("DE73500105177492489759")
            .clickOnContinueForMcd()
            .clickOnOkForDepositChallenge()
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

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_mcd_transaction_for_gb_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
            .selectUser("GB - Freddie Mercury")
            .selectLanguage("English (en)")
            .enableDeveloperOptionsWith((options) -> options
                .selectPaymentProvider("Manual")
                .selectManualPaymentProviderSubtype("MCD"))
            .openLightBoxThroughLogInToMyBankButton();

        lightBoxComponent
            .fillFirstName("Caio Cesar")
            .fillLastName("Gava")
            .fillSortCode("400200")
            .fillAccountNumber("65460271")
            .clickOnContinueForMcd()
            .clickOnOkForDepositChallenge()
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

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_mcd_transaction_for_ca_successfully(ITestContext context) {
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
                .selectManualPaymentProviderSubtype("MCD"))
            .openLightBoxInBankList();

        lightBoxComponent
            .fillNameOnAccount("Caio Gava")
            .fillTransitNumber("16832")
            .fillFinancialInstitutionNumber("002")
            .fillAccountNumber("5909908")
            .selectAccountType("Personal - Checking")
            //.clickOnContinueForMcd() TODO CHECK IF INT E UAT WORK
            .clickOnOkForDepositChallenge()
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

    @Test(groups = { "sanity" })
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

    @Test(groups = { "sanity" })
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

    @Test(groups = { "sanity" })
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

    @Test(groups = { "sanity" })
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

    @Test(groups = { "sanity" })
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

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_mec_transaction_for_au_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
            .selectUser("AU - Daniel Johns")
            .enableDeveloperOptionsWith((options) -> options
                .selectPaymentProvider("Manual")
                .selectManualPaymentProviderSubtype("MED/MEC"))
            .openLightBoxInBankList();

        lightBoxComponent
            .startFlow(() -> {
                var flow = new MecAuFlow();

                flow
                    .fillNameOnAccount("Caio Gava")
                    .fillAccountNumber("12345678")
                    .fillAccountNumberConfirmation("12345678")
                    .fillBSB("032054")
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

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_mec_transaction_for_gb_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
            .selectUser("GB - Freddie Mercury")
            .enableDeveloperOptionsWith((options) -> options
                .selectPaymentProvider("Manual")
                .selectManualPaymentProviderSubtype("MED/MEC"))
            .openLightBoxThroughLogInToMyBankButton();

        lightBoxComponent
            .startFlow(() -> {
                var flow = new MecGbFlow();

                flow
                    .fillNameOnAccount("Caio Gava")
                    .fillSortCode("400200")
                    .fillAccountNumber("65460271")
                    .fillAccountNumberConfirmation("65460271")
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

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_mec_transaction_for_de_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("5.00")
            .selectUser("DE - Franz Beckenbauer")
            .selectLanguage("English (en)")
            .enableDeveloperOptionsWith((options) -> options
                .selectPaymentProvider("Manual")
                .selectManualPaymentProviderSubtype("MED/MEC"))
            .openLightBoxInBankList();

        lightBoxComponent
            .startFlow(() -> {
                var flow = new MecDeFlow();

                flow
                    .fillNameOnAccount("Caio Gava")
                    .fillIBAN("DE73500105177492489759")
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

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_psd2_transaction_for_fr_successfully(ITestContext context) {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (automation)")
            .selectUseCase("Retrieval")
            .fillAmountWith("0.00")
            .selectUser("FR - Zinedine Zidane")
            .selectLanguage("English (en)")
            .openLightBoxThroughLogInToMyBankButton();

        lightBoxComponent
            .selectBank("Demo Bank")
            .startFlow(() -> {
                var flow = new DemoBankPsd2Flow();

                flow
                    .clickOnGoToDemoBank()
                    .clickOnLogin()
                    .clickOnAccept()
                    .clickOnContinue()
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

    @Test(groups = { "sanity" })
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

    @Test()
    public void should_create_a_deferred_transaction_using_homeclick_successfully(ITestContext context) {
        HomeClickPage homeClick = new HomeClickPage(Deferred);
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

    @Test(groups = { "sanity" })
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

    @Test(groups = { "sanity" })
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

    @Test(groups = { "sanity" })
    public void should_create_a_verification_transaction_using_wester_union_successfully(ITestContext context) {
        WesterUnionPage westerUnion = new WesterUnionPage(WesterUnionPage.PaymentType.Verification);
        LightBoxComponent lightBoxComponent = westerUnion
            .load()
            .addNewBankAccountClick()
            .populateArrowClick();

        lightBoxComponent
            .startFlow(() -> {
                var flow = new DemoBankWesterUnionFlow();

                flow
                    .fillUserNameWith("RandomAccounts")
                    .fillPasswordWith("RandomAccounts")
                    .clickOnContinueButton();
            })
            .waitUntilClose();

        westerUnion.waitRedirectUrl();

        //westerUnion.assertVerificationStatusIsAuthorized(); // TODO environment is not sending this information back to the page

        var redirectedUrl = webdriver().driver().url();
        //var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        context.setAttribute(context.getName() + " url", redirectedUrl);
        //context.setAttribute(context.getName() + " succeeded", succeeded);
        context.setAttribute(context.getName() + " transactionId", transactionId);

        //assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());
    }

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_transaction_using_wester_union_successfully(ITestContext context) {
        WesterUnionPage westerUnion = new WesterUnionPage(WesterUnionPage.PaymentType.Retrieval);
        EmbeddedLightBoxComponent embeddedLightBoxComponent = westerUnion
            .load()
            .selectBank("Demo Bank")
            .clickOnContinueButton();

        embeddedLightBoxComponent
            .startFlow(() -> {
                var flow = new DemoBankWesterUnionEmbeddedFlow();

                flow
                    .fillUserNameWith("RandomAccounts")
                    .fillPasswordWith("RandomAccounts")
                    .clickOnContinueButton()
                    .clickOnContinueAccountButton();
            });

        westerUnion.waitRedirectUrl();
        westerUnion.waitUntilSuccessMessage();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        context.setAttribute(context.getName() + " url", redirectedUrl);
        context.setAttribute(context.getName() + " succeeded", succeeded);
        context.setAttribute(context.getName() + " transactionId", transactionId);

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());
    }

    @Test(groups = { "sanity" })
    public void should_create_a_retrieval_transaction_for_att_successfully(ITestContext context) {
        AttPage att = new AttPage();
        LightBoxComponent lightBoxComponent = att
            .load()
            .openLightBoxInBankList();

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
                    .clickOnSubmitAccount()
                ;
            })
            .waitUntilClose();

        att.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        context.setAttribute(context.getName() + " url", redirectedUrl);
        context.setAttribute(context.getName() + " succeeded", succeeded);
        context.setAttribute(context.getName() + " transactionId", transactionId);

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());
    }

    @Test(groups = { "sanity" })
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

    @Test(groups = { "sanity" }) // TODO CRIAR NOVO FLOW POIS ESSE NAO USA OS NOVOS ID #lbx-formAuthenticate-authFields-inputusername
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

