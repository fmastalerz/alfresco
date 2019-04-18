import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    @Test
    void seleniumTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://127.0.0.1:8080/share/page/user/admin/dashboard");

        WebElement username = driver.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-username"));
        username.sendKeys("admin");

        WebElement password = driver.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-password"));
        password.sendKeys("admin123");

        WebElement button = driver.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-submit-button"));
        button.click();

        //driver.quit();
    }
}
