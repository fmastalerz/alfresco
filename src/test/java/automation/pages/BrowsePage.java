package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowsePage {
    private WebDriver driver;
    private By groupIdentifier;

    public BrowsePage(WebDriver driver, String displayName, String identifier) {
        this.driver = driver;
        this.groupIdentifier = By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier));
    }

    public String getGroupName() {
        WebElement spanWithGroupName = driver.findElement(groupIdentifier);
        return spanWithGroupName.getText();
    }
}
