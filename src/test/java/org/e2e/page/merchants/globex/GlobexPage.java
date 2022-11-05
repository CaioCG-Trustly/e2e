package org.e2e.page.merchants.globex;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.e2e.config.execution.EnvBaseUrl;
import org.e2e.config.execution.ExecutionConfig;
import org.e2e.page.merchants.globex.components.DeveloperOptionComponent;
import org.e2e.page.merchants.globex.components.LightBoxComponent;
import org.e2e.page.merchants.globex.components.WidgetComponent;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.Selenide.webdriver;

public class GlobexPage {
    static final String localPort = ":7000";

    static final SelenideElement bySelectMerchant = $("#merchantList");
    static final SelenideElement bySelectPaymentType = $("#paymentType");
    static final SelenideElement byInputAmount = $("#amountInput");
    static final SelenideElement bySelectUx = $(By.xpath("//*[@id=\"filters\"]/div[4]/select"));
    static final SelenideElement bySelectUser = $(By.xpath("//select[@name='customer']"));
    static final SelenideElement bySelectCurrency = $(By.xpath("//select[@name='currency']"));
    static final SelenideElement bySelectLanguage = $(By.xpath("//select[@name='language']"));
    static final SelenideElement byCheckBoxDeveloperOptions = $(".customCheckBox:nth-child(1) > span");
    static final SelenideElement byInputCustomerName = $("#customerName");
    static final SelenideElement byInputCustomerZipCode = $("#customerZip");
    static final SelenideElement byButtonSetCustomer = $("#customer-set");

    final String url;
    final DeveloperOptionComponent developerOptionComponent;
    final WidgetComponent widgetComponent;

    public GlobexPage() {
        this.url = "%s/merchant-demo/globex%s".formatted(handleBaseUrl(), handleOptimizationForTesting());
        developerOptionComponent = new DeveloperOptionComponent();
        widgetComponent = new WidgetComponent();
    }

    public GlobexPage load() {
        open(url);
        return this;
    }

    public GlobexPage refresh() {
        Selenide.refresh();
        return this;
    }

    public GlobexPage selectMerchant(String merchant) {
        bySelectMerchant.selectOption(merchant);

        var isPageReady = false;
        do {
            sleep(200);
            isPageReady = ("Globex Demo · [" + merchant + "]").equals(title());

        } while(!isPageReady);

        return this;
    }

    public GlobexPage selectUseCase(String transactionType) {
        bySelectPaymentType.selectOption(transactionType);
        return this;
    }

    public GlobexPage fillAmountWith(String amount) {
        byInputAmount.clear();
        byInputAmount.sendKeys(amount);
        return this;
    }

    public GlobexPage selectUx(String ux) {
        bySelectUx.selectOption(ux);
        return this;
    }

    public GlobexPage selectUser(String user) {
        bySelectUser.selectOption(user);
        return this;
    }

    public GlobexPage fillUserName(String name) {
        byInputCustomerName.clear();
        byInputCustomerName.sendKeys(name);
        return this;
    }

    public GlobexPage fillZipCode(String zipCode) {
        byInputCustomerZipCode.clear();
        byInputCustomerZipCode.sendKeys(zipCode);
        return this;
    }

    public GlobexPage clickOnSetCustomer() {
        byButtonSetCustomer.click();
        return this;
    }

    public GlobexPage selectCurrency(String currency) {
        bySelectCurrency.selectOption(currency);
        return this;
    }

    public GlobexPage selectLanguage(String language) {
        bySelectLanguage.selectOption(language);
        return this;
    }

    public GlobexPage enableDeveloperOptionsWith(DeveloperOptionComponent.DeveloperOptions options) {
        byCheckBoxDeveloperOptions.click();
        options.execute(developerOptionComponent);
        return this;
    }

    public LightBoxComponent openLightBoxInBankList() {
        return widgetComponent
            .clickAllOtherBanks();
    }

    public LightBoxComponent openLightBoxByBankLogoName(String bankName) {
        return widgetComponent
            .clickByBankLogoName(bankName);
    }

    public LightBoxComponent openLightBoxThroughLogInToMyBankButton() {
        return widgetComponent
            .clickLogInToMyBank();
    }

    public LightBoxComponent openLightBoxThroughInputSearch(String bankName) {
        return widgetComponent
            .searchFor(bankName);
    }
//    public LightBoxComponent openLightBoxInAccountDetails() {
//        return widgetComponent
//            .clickAllOtherBanks();
//    }

    private String handleBaseUrl() {
        var baseUrl = EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment).baseUrl;
        var port = EnvBaseUrl.Local.equals(
            EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment)
        ) ? localPort : "";

        return baseUrl + port;
    }

    private String handleOptimizationForTesting() {
        return ExecutionConfig.globexOptimizationForTesting ? "/?testing=true" : "";
    }

    public void waitRedirectUrl() {
        var timeToWait = List.of(1000, 2000, 3000, 5000);
        var count = 0;

        while(Objects.equals(this.url, webdriver().driver().url())) {
            sleep(timeToWait.get(count));
            count++;
        }
    }
}
