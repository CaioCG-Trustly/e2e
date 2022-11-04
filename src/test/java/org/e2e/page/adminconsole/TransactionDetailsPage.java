package org.e2e.page.adminconsole;

import org.e2e.config.execution.EnvBaseUrl;
import org.e2e.config.execution.ExecutionConfig;
import org.e2e.page.adminconsole.tabs.EventsTabComponent;
import org.e2e.page.adminconsole.tabs.RiskAnalysisTabComponent;
import org.e2e.page.adminconsole.tabs.TransactionTabComponent;

import static com.codeborne.selenide.Selenide.open;

public class TransactionDetailsPage {
    static final String localPort = ":9002";
    public final EventsTabComponent eventsTab;
    public final TransactionTabComponent transactionTab;
    public final RiskAnalysisTabComponent riskAnalysisTab;

    String url;

    public TransactionDetailsPage() {
        this.eventsTab = new EventsTabComponent();
        this.transactionTab = new TransactionTabComponent();
        this.riskAnalysisTab = new RiskAnalysisTabComponent();
    }

    public void load(String transactionId) {
        this.url = "%s/admin-console/transactions/details/%s".formatted(this.handleBaseUrl(), transactionId);
        open(url);
    }

    public void loadEventsTab() {
        open(this.url + "?tab=events");
    }

    public void loadTransactionTab() {
        open(this.url + "?tab=transaction");
    }

    public void loadRiskAnalysisTab() {
        open(this.url + "?tab=risk-log");
    }

    private String handleBaseUrl() {
        var baseUrl = EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment).baseUrl;
        var port = EnvBaseUrl.Local.equals(
            EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment)
        ) ? localPort : "";

        return baseUrl + port;
    }
}
