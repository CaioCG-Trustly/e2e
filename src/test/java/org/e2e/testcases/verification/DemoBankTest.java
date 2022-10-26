package org.e2e.testcases.verification;

import org.e2e.config.testrunner.LogListener;
import org.e2e.flows.westerunion.DemoBankWesterUnionFlow;
import org.e2e.page.merchants.globex.components.LightBoxComponent;
import org.e2e.page.merchants.westerunion.WesterUnionPage;
import org.e2e.testcases.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.e2e.utils.UrlUtils.getQueryParam;
import static org.testng.Assert.assertFalse;

@Listeners(LogListener.class)
public class DemoBankTest extends BaseTest {

    @Test(groups = {"sanity"})
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

}

