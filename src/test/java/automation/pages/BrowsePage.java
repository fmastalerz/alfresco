package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowsePage {
    private WebDriver driver;

    public BrowsePage(WebDriver driver) {
        this.driver = driver;
    }



    public String getGroupName() {
        WebElement spanWithGroupName = driver.findElement(By.xpath("//span[contains(text(),'SomeGroup')]"));
        return spanWithGroupName.getText();
    }
}
