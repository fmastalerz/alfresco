package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowseGroupsPanel {
    private WebDriver driver;
    private By groupCredentialsPath;

    public BrowseGroupsPanel(WebDriver driver) {
        this.driver = driver;
    }

    public BrowseGroupsPanel setGroupCredentialsPath(String displayName, String identifier) {
        String path = String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier);
        this.groupCredentialsPath = By.xpath(path);
        return this;
    }

    public String getGroupName() {
        return driver.findElement(groupCredentialsPath).getText();
    }

    public By getGroupCredentialsPath() {
        return groupCredentialsPath;
    }

    public WebElement getGroup() {
        WebElement group;
        return group = driver.findElement(groupCredentialsPath);
    }
}
