package org.e2e.page.merchants.globex.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DeveloperOptionComponent {

    static final SelenideElement bySelectPaymentProvider = $(By.name("paymentProviderTypes"));
    static final SelenideElement bySelectManualPaymentProviderSubtype = $(By.name("manualPPSubtype"));

    public DeveloperOptionComponent selectPaymentProvider(String paymentProvider) {
        bySelectPaymentProvider.selectOption(paymentProvider);
        return this;
    }

    public DeveloperOptionComponent selectManualPaymentProviderSubtype(String paymentProviderSubtype) {
        bySelectManualPaymentProviderSubtype.selectOption(paymentProviderSubtype);
        return this;
    }

    @FunctionalInterface
    public interface DeveloperOptions {
        void execute(DeveloperOptionComponent developerOptionComponent);
    }
}
