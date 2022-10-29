package org.e2e.page.adminconsole.tabs;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class EventsTabComponent {
    static final SelenideElement byTable = $("#sortabletable > tbody");

    public String getAttributesByEventType(String eventType) {
        var rows = byTable.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            var eventTypeTable = row.findElement(By.cssSelector("td:nth-child(2)"));
            if (eventType.equals(eventTypeTable.getText().trim())) {
                return row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            }
        }

        throw new RuntimeException("Could not find the event type: " + eventType);
    }
}
