package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By nameSpanLocator = By.id("HEADER_USER_MENU_POPUP_text");

    public String nameFromSpan() {
        return driver.findElement(nameSpanLocator).getText();
    }
}
