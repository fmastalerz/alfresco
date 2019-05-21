package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowsePage {
    private WebDriver driver;
    private By groupIdentifier = By.xpath("//span[contains(text(),'NewGroup (Group)')]");

    public BrowsePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getGroupName() {
        WebElement spanWithGroupName = driver.findElement(groupIdentifier);
        return spanWithGroupName.getText();
    }
}
