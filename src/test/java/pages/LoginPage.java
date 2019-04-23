package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    By usernameLocator = By.id("page_x002e_components_x002e_slingshot-login_x0023_default-username");
    By passwordLocator = By.id("page_x002e_components_x002e_slingshot-login_x0023_default-password");
    By loginButtonLocator = By.id("page_x002e_components_x002e_slingshot-login_x0023_default-submit-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage typeUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public HomePage submitLogin() {
        driver.findElement(loginButtonLocator).submit();
        return new HomePage(driver);
    }
}
