package org.e2e.page.merchants.globex.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;

public class WidgetComponent {

    static final SelenideElement byIFrameWidget = $("#paywithmybank-iframe-widget-container");
    static final SelenideElement byButtonAllOtherBanks = $(".bank-item--others-text-title");
    static final SelenideElement byButtonLogInToMyBanks = $("#bankLogin");

    static final SelenideElement byInputSearchBar = $("#widgetSearchField");

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

    public LightBoxComponent clickByBankLogoName(String bankName) {
        switchToWidgetIFrame();
        $(By.xpath("//img[@alt='" + bankName + "']")).click();
        return lightBoxComponent;
    }

    public LightBoxComponent searchFor(String bankName) {
        switchToWidgetIFrame();
        byInputSearchBar.sendKeys(bankName);
        // TODO IMPROVE THIS SLEEP TO WAIT FOR SOME OTHER CONDITION
        sleep(2000); // It takes some time to have the results renders
        $(By.xpath("//*[@id='widgetSearchList']/li[1]/div")).click();
        return lightBoxComponent;
    }

    private void switchToWidgetIFrame() {
        switchTo().frame(byIFrameWidget);
    }
}
