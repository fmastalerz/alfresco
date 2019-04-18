package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage {
    WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement spanWithUsersRole() {
        return driver.findElement(By.id("HEADER_USER_MENU_POPUP_text"));
    }
}
