package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrowseGroupsPanel {
    private WebDriver driver;
    private By groupCredentialsPath;
    private JavascriptExecutor jsExecutor;

    public BrowseGroupsPanel(WebDriver driver) {
        this.driver = driver;
    }

    //todo: clear this mess
    public BrowseGroupsPanel setGroupCredentialsPath(String displayName, String identifier) {
        String path = String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier);
        this.groupCredentialsPath = By.xpath(path);
        return this;
    }

    public void findGroupToRemove(String groupToRemove) {
        this.jsExecutor = jsExecutor = ((JavascriptExecutor)driver);

        WebElement groupSpan = getRemoveGroupSpan(groupToRemove);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", groupSpan);

        getRemoveButton(groupToRemove);

        WebElement deleteButton =
                driver.findElement(By.id("page_x002e_ctool_x002e_admin-console_x0023_default-remove-button-button"));
        deleteButton.click();

    }

    // fix: this
    private WebElement getRemoveButton(String groupToRemove) {
        String path = String.format("//a[@class='yui-columnbrowser-item groups-item-group']//span[contains(text(),'%s')]/following-sibling::span[1]/span[@class='groups-delete-button']", groupToRemove);
        By pathToButton = By.xpath(path);
        WebElement removeButton =
                driver.findElement(pathToButton);
        return removeButton;
    }

    private WebElement getRemoveGroupSpan(String groupToRemove) {
        String pathToSpan = String.format("//*[contains(text(), '%s')]/parent::*", groupToRemove);
        return driver.findElement(By.xpath(pathToSpan));
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

    public void removeGroup(String groupToRemove) {
        getRemoveGroupSpan(groupToRemove);
        WebElement removeButton = getRemoveButton(groupToRemove);
        clickButton(removeButton);
    }

    private void clickButton(WebElement button) {
        button.click();
    }

    public boolean isFound(String groupToRemove) {
        List<WebElement> groups =
                driver.findElements(By.xpath("//a[@class='yui-columnbrowser-item groups-item-group']"));

        return groups.stream().anyMatch(element -> element.getText().equals(groupToRemove));
    }
}
