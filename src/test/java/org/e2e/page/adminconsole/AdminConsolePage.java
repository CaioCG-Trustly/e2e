package org.e2e.page.adminconsole;

import org.e2e.config.execution.EnvBaseUrl;
import org.e2e.config.execution.ExecutionConfig;

import static org.testng.Assert.fail;

public class AdminConsolePage {
    static final String localPort = ":9002";
    final String url;

    final TransactionDetailsPage transactionDetailsPage;

    public AdminConsolePage() {
        this.url = "%s/admin-console".formatted(handleBaseUrl());
        transactionDetailsPage = new TransactionDetailsPage();
    }

    public AdminConsolePage loadInTransactionDetailsPage(String transactionId) {
        transactionDetailsPage.load(transactionId);
        return this;
    }

    public AdminConsolePage goToEventsTab() {
        transactionDetailsPage.loadEventsTab();
        return this;
    }

    public AdminConsolePage goToTransactionTab() {
        transactionDetailsPage.loadTransactionTab();
        return this;
    }

    public AdminConsolePage goToRiskAnalysisTab() {
        transactionDetailsPage.loadRiskAnalysisTab();
        return this;
    }

    private String handleBaseUrl() {
        var baseUrl = EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment).baseUrl;
        var port = EnvBaseUrl.Local.equals(
            EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment)
        ) ? localPort : "";

        return baseUrl + port;
    }

    public AdminConsolePage assertThat(Assertions assertions) {
        try {
            assertions.execute(transactionDetailsPage);
        } catch (Exception e) {
            fail();
        }
        return this;
    }

    @FunctionalInterface
    public interface Assertions {
        void execute(TransactionDetailsPage t);
    }
}
