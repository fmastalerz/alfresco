package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement userName(){
        return driver.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-username"));
    }

    public WebElement password(){
        return driver.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-password"));
    }

    public WebElement button(){
        return driver.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-submit-button"));
    }

    public WebElement spanWithUsersRole() {
        return driver.findElement(By.id("HEADER_USER_MENU_POPUP_text"));
    }
}
