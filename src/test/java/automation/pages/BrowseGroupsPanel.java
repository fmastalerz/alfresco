package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowseGroupsPanel extends PageObject{
    private By groupCredentials;

    private WebElement newGroupSpan;

    @FindBy(id = "page_x002e_ctool_x002e_admin-console_x0023_default-remove-button-button")
    private WebElement deleteButton;

    //todo: clean this
    @FindBy(xpath = "//div[@class='yui-columnbrowser-column-body']")
    private WebElement groupsTable;

    @FindBy(xpath = "//a[@class='yui-columnbrowser-item spansWithGroups-item-group']")
    private List<WebElement> spansWithGroups;

    private By xPathToGroupsTable = By.xpath("//div[@class='yui-columnbrowser-column-body']");

    public BrowseGroupsPanel(WebDriver driver) {
        super(driver);
    }

    public void setNewGroupSpan(String displayName, String identifier) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        By xpath = By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier));
        this.newGroupSpan = driver.findElement(xpath);
    }

    public String getNewGroupName() {
        String name = newGroupSpan.getText();
        return name;
    }

    public BrowseGroupsPanel setGroupCredentialsPath(String displayName, String identifier) {

        this.groupCredentials = By.xpath(String.format("//span[contains(text(),'%s (%s)')]", displayName, identifier));
        return this;
    }

    public By getGroupCredentialsPath() {
        return groupCredentials;
    }

    public String getGroupName() {
        return driver.findElement(groupCredentials).getText();
    }


    public WebElement getRemoveGroupSpan(String groupToRemove) {
        String pathToSpan = String.format("//*[contains(text(), '%s')]/parent::*", groupToRemove);
        WebElement groupSpan = driver.findElement(By.xpath(pathToSpan));
        return groupSpan;
    }

    public WebElement getRemoveGroupButton(String groupToRemove) {
        String path = String.format("//a[@class='yui-columnbrowser-item spansWithGroups-item-group']//span[contains(text(),'%s')]/following-sibling::span[1]/span[@class='spansWithGroups-delete-button']", groupToRemove);
        WebElement removeButton = driver.findElement(By.xpath(path));
        return removeButton;
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }

    public boolean isGroupOnList(String groupToRemove) {
        boolean isFound = spansWithGroups.stream().anyMatch(element -> element.getText().equals(groupToRemove));
        return isFound;
    }

    public void removeGroup(JavascriptExecutor jsExecutor, String groupToRemove) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getRemoveGroupSpan(groupToRemove));
        jsExecutor.executeScript("arguments[0].click();", getRemoveGroupButton(groupToRemove));
        clickDeleteButton();
    }

    //todo: clean this
    public WebElement getGroupsTable() {
        return groupsTable;
    }

    public By getxPathToGroupsTable() {
        return xPathToGroupsTable;
    }

}
