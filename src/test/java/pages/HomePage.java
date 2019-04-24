package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By nameSpanLocator = By.id("HEADER_USER_MENU_POPUP_text");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String nameFromSpan() {
        return driver.findElement(nameSpanLocator).getText();
    }
}
