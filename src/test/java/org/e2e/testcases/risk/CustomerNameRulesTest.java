package org.e2e.testcases.risk;

import org.e2e.connectors.demobank.DemoBankDefaultFlow;
import org.e2e.page.adminconsole.AdminConsolePage;
import org.e2e.page.merchants.globex.GlobexPage;
import org.e2e.page.merchants.globex.components.LightBoxComponent;
import org.e2e.testcases.BaseTest;
import org.e2e.utils.AdminConsoleUtils;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.e2e.utils.UrlUtils.getQueryParam;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CustomerNameRulesTest extends BaseTest {

    @Test(groups = {"risk_name_matching"})
    public void should_xxxxxxx_successfully() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("John Smith II")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            })
        ;
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_2() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith")
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");


        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("AccountProfile")
                    .fillPasswordWith("AccountProfile")
                    .clickLoginButton()
                    .selectAccountWithName("Business Demo Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertFalse(attributes.contains("customer.name"));
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_3_variant_john_smith_II() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("John Smith II")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_3_variant_johnny_smith_II() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Johnny Smith II")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            })
        ;
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_3_variant_dr_john_smith() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Dr. John Smith")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_4_variant_johnny() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Johnny")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("RandomAccounts")
                    .fillPasswordWith("RandomAccounts")
                    .clickLoginButton()
                    .selectAccountWithName("Random Savings Account")
                    .clickOnSubmitAccount();
            })
            .close()
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: NoMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var statusCode = transactionDetailsPage.transactionTab.getAttributeByProperty("statusCode:");

                assertEquals(statusCode, "SW054");
            })
            .goToRiskAnalysisTab()
            .assertThat((transactionDetailsPage) -> {
                var reason = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reason");
                var reasonCode = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reasoncode");

                assertEquals(reason, "Customer information does not match");
                assertEquals(reasonCode, "DataMismatch");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_4_variant_john_johnny_smith() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("John Johnny Smith")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_5_variant_john_smith_alfredo() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("John Smith Alfredo")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_5_variant_johnny_smith_alfredo() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Johnny Smith Alfredo")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_5_variant_sarah_smith_alfredo() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Sarah Smith Alfredo")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("RandomAccounts")
                    .fillPasswordWith("RandomAccounts")
                    .clickLoginButton()
                    .selectAccountWithName("Random Savings Account")
                    .clickOnSubmitAccount();
            })
            .close()
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: NoMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var statusCode = transactionDetailsPage.transactionTab.getAttributeByProperty("statusCode:");

                assertEquals(statusCode, "SW054");
            })
            .goToRiskAnalysisTab()
            .assertThat((transactionDetailsPage) -> {
                var reason = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reason");
                var reasonCode = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reasoncode");

                assertEquals(reason, "Customer information does not match");
                assertEquals(reasonCode, "DataMismatch");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_6_variant_mary_allie_smith() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Mary Allie Smith")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSecondName")
                    .fillPasswordWith("CustomerWithSecondName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: FullMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    // TODO ESTE TESTE DEMANDOU A TROCA DE LOCALIZADORES DE RISK ANALYSIS
    public void scenario_6_variant_maria_alice_smith() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Maria Alice Smith")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSecondName")
                    .fillPasswordWith("CustomerWithSecondName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .close()
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: NoMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var statusCode = transactionDetailsPage.transactionTab.getAttributeByProperty("statusCode:");

                assertEquals(statusCode, "SW054");
            })
            .goToRiskAnalysisTab()
            .assertThat((transactionDetailsPage) -> {
                var reason = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reason");
                var reasonCode = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reasoncode");

                assertEquals(reason, "Customer information does not match");
                assertEquals(reasonCode, "DataMismatch");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_6_variant_maria_lisa_von() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Maria Lisa Von")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSecondName")
                    .fillPasswordWith("CustomerWithSecondName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .close()
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: NoMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var statusCode = transactionDetailsPage.transactionTab.getAttributeByProperty("statusCode:");

                assertEquals(statusCode, "SW054");
            })
            .goToRiskAnalysisTab()
            .assertThat((transactionDetailsPage) -> {
                var reason = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reason");
                var reasonCode = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reasoncode");

                assertEquals(reason, "Customer information does not match");
                assertEquals(reasonCode, "DataMismatch");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_7_variant_john_von_and_matching_zip_code() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("John Von")
            .fillZipCode("90210")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSecondName")
                    .fillPasswordWith("CustomerWithSecondName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .close()
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: NoMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var statusCode = transactionDetailsPage.transactionTab.getAttributeByProperty("statusCode:");

                assertEquals(statusCode, "SW054");
            })
            .goToRiskAnalysisTab()
            .assertThat((transactionDetailsPage) -> {
                var reason = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reason");
                var reasonCode = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reasoncode");

                assertEquals(reason, "Customer information does not match");
                assertEquals(reasonCode, "DataMismatch");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_7_variant_john_smith_and_no_matching_zip_code() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("John Smith")
            .fillZipCode("90111")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: FullMatch"));
                assertTrue(attributes.contains("customer.address.zip: NoMatch"));
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_7_variant_sarah_smith_and_no_matching_zip_code() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Sarah Smith")
            .fillZipCode("90111")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("RandomAccounts")
                    .fillPasswordWith("RandomAccounts")
                    .clickLoginButton()
                    .selectAccountWithName("Random Savings Account")
                    .clickOnSubmitAccount();
            })
            .close()
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: NoMatch"));
                assertTrue(attributes.contains("customer.address.zip: NoMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var statusCode = transactionDetailsPage.transactionTab.getAttributeByProperty("statusCode:");

                assertEquals(statusCode, "SW054");
            })
            .goToRiskAnalysisTab()
            .assertThat((transactionDetailsPage) -> {
                var reason = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reason");
                var reasonCode = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reasoncode");

                assertEquals(reason, "Customer information does not match");
                assertEquals(reasonCode, "DataMismatch");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_8_variant_john_smith_and_matching_zip_code() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("John Smith")
            .fillZipCode("90210")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("NoCustomerName")
                    .fillPasswordWith("NoCustomerName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: CouldNotVerify"));
                assertTrue(attributes.contains("customer.address.zip: FullMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_8_variant_john_smith_and_no_matching_zip_code() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("John Smith")
            .fillZipCode("90111")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("NoCustomerName")
                    .fillPasswordWith("NoCustomerName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: CouldNotVerify"));
                assertTrue(attributes.contains("customer.address.zip: NoMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_9_variant_johnsmith() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("JohnSmith")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_9_variant_new_line_between_names() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("John-Smith")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: FullMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_9_variant_smith_john() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Smith John")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_9_variant_john_smith_uppercase() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("JohH SmItH")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
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

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_10_variant_andre_weiss() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("André Weiss")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSpecialCharactersOnName")
                    .fillPasswordWith("CustomerWithSpecialCharactersOnName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: FullMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_10_variant_pedro_machado() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("PEDRO M’ACHADO")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSpecialCharactersOnName")
                    .fillPasswordWith("CustomerWithSpecialCharactersOnName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: FullMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_10_variant_sarah_mazzoti() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("SARAH M'AZZOTTI")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSpecialCharactersOnName")
                    .fillPasswordWith("CustomerWithSpecialCharactersOnName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: PartialMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_10_variant_bruno_mazzoti_special_char() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("BRUNø M'AZZOTTI")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSpecialCharactersOnName")
                    .fillPasswordWith("CustomerWithSpecialCharactersOnName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: FullMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_10_variant_joao_scharf() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("JOÃO S&#39;CHARF")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSpecialCharactersOnName")
                    .fillPasswordWith("CustomerWithSpecialCharactersOnName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: FullMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_10_variant_priscila_ferreira() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("PRISCILA F`ERREIRA")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSpecialCharactersOnName")
                    .fillPasswordWith("CustomerWithSpecialCharactersOnName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var succeeded = getQueryParam(redirectedUrl, "success");
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertEquals(succeeded, "true");
        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: FullMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var status = transactionDetailsPage.transactionTab.getAttributeByProperty("status:").trim();

                assertEquals(status, "Authorized");
            });
    }

    @Test(groups = {"risk_name_matching"})
    public void scenario_10_variant_pri_di_ferreira() {
        GlobexPage globex = new GlobexPage();
        LightBoxComponent lightBoxComponent = globex
            .load()
            .selectMerchant("Globex (demo)")
            .selectUseCase("Instant")
            .fillAmountWith("5.00")
            .selectUser("US - John Smith") // setting up information before changing to "Custom"
            .selectUser("Custom")
            .fillUserName("Pri D’Ferreira")
            .clickOnSetCustomer()
            .selectLanguage("English (en)")
            .openLightBoxThroughInputSearch("Demo Bank");

        lightBoxComponent
            .switchToLightBoxIFrame()
            .startFlow(() -> {
                var flow = new DemoBankDefaultFlow();

                flow
                    .dismissSlider()
                    .fillUserNameWith("CustomerWithSpecialCharactersOnName")
                    .fillPasswordWith("CustomerWithSpecialCharactersOnName")
                    .clickLoginButton()
                    .selectAccountWithName("Demo Savings Account")
                    .clickOnSubmitAccount();
            })
            .close()
            .waitUntilClose();

        globex.waitRedirectUrl();

        var redirectedUrl = webdriver().driver().url();
        var transactionId = getQueryParam(redirectedUrl, "transactionId");

        assertFalse(transactionId.isBlank());

        AdminConsoleUtils.loginAdminConsole();

        AdminConsolePage adminConsole = new AdminConsolePage();
        adminConsole
            .loadInTransactionDetailsPage(transactionId)
            .goToEventsTab()
            .assertThat((transactionDetailsPage) -> {
                var attributes = transactionDetailsPage.eventsTab.getAttributesByEventType("VerifyCustomer");

                assertTrue(attributes.contains("customer.name: NoMatch"));
            })
            .goToTransactionTab()
            .assertThat((transactionDetailsPage) -> {
                var statusCode = transactionDetailsPage.transactionTab.getAttributeByProperty("statusCode:");

                assertEquals(statusCode, "SW054");
            })
            .goToRiskAnalysisTab()
            .assertThat((transactionDetailsPage) -> {
                var reason = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reason");
                var reasonCode = transactionDetailsPage.riskAnalysisTab.getAttributeByProperty("ruleengine.deny.reasoncode");

                assertEquals(reason, "Customer information does not match");
                assertEquals(reasonCode, "DataMismatch");
            });
    }
}

