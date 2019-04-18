import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class LoginPageTest {
    public static WebDriver driver;
    public LoginPage loginPage;


    @Test
    void checkIfLoginDefaultUser() {
        loginPage.userName().sendKeys("admin");
        loginPage.password().sendKeys("admin123");
        loginPage.button().click();
    }

    @BeforeEach
    public void beforeEach(){
        driver.get("http://127.0.0.1:8080/share/page/");
        loginPage = new LoginPage(driver);
    }

    @BeforeAll
    public static void beforeAll(){
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll(){
       driver.quit();
    }

}
