import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AdminPage;
import pages.LoginPage;

public class LoginPageTest {
    public static WebDriver driver;
    public LoginPage loginPage;
    public AdminPage adminPage;

    @Test
    void checkIfLoginDefaultUser() {
        loginPage.userName().sendKeys("admin");
        loginPage.password().sendKeys("admin123");
        loginPage.button().click();

        //todo: sent this part to the proper place as soon as possible
        driver.get("http://127.0.0.1:8080/share/page/user/admin/dashboard");
        adminPage = new AdminPage(driver);

        assertEquals("Administrator", adminPage.spanWithUsersRole().getText());
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
       //driver.quit();
    }

}
