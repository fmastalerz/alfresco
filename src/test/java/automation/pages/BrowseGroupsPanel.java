package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowseGroupsPanel {
    private WebDriver driver;
    private By groupCredentials;
    private WebElement group;

    public BrowseGroupsPanel(WebDriver driver) {
        this.driver = driver;
    }

    public void setPathToGroupCredentials(String displayName, String identifier) {
        this.groupCredentials = By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier));
    }

    public String getGroupName() {
        return driver.findElement(groupCredentials).getText();
    }

    public By getGroupCredentials() {
        return this.groupCredentials;
    }

    public WebElement getElementWithGroupIdentifier() {
        return group = driver.findElement(groupCredentials);
    }
}
