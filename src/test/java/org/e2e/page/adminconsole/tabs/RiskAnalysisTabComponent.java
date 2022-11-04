package org.e2e.page.adminconsole.tabs;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class RiskAnalysisTabComponent {

    //    static final SelenideElement byTable = $("#fraud-analysis-result > table > tbody");
    static final SelenideElement byTable = $("#fraud-analysis-result > div > div > table > tbody");

    public String getAttributeByProperty(String property) {
        var rows = byTable.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            var attributeTable = row.findElement(By.cssSelector("td:nth-child(1)"));
            if (property.equals(attributeTable.getText().trim())) {
                return row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            }
        }

        throw new RuntimeException("Could not find the property on Risk Analysis tab: " + property);
    }
}
