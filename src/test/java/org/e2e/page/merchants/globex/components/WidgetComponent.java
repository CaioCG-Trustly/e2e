package org.e2e.page.merchants.globex.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class WidgetComponent {

    static final SelenideElement byIFrameWidget = $("#paywithmybank-iframe-widget-container");
    static final SelenideElement byButtonAllOtherBanks = $(".bank-item--others-text-title");
    static final SelenideElement byButtonLogInToMyBanks = $("#bankLogin");

    final LightBoxComponent lightBoxComponent;

    public WidgetComponent() {
        lightBoxComponent = new LightBoxComponent();
    }


    public LightBoxComponent clickAllOtherBanks() {
        switchToWidgetIFrame();
        byButtonAllOtherBanks.click();
        return lightBoxComponent;
    }

    public LightBoxComponent clickLogInToMyBank() {
        switchToWidgetIFrame();
        byButtonLogInToMyBanks.click();
        return lightBoxComponent;
    }

    public WidgetComponent searchFor(String bankName) {
        // TODO validate if can be used for widget with search bar
        return this;
    }

    private void switchToWidgetIFrame() {
        switchTo().frame(byIFrameWidget);
    }
}
