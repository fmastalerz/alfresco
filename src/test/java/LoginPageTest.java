import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {
    LoginPage loginPage;
    static WebDriver driver;
    static ConfigLoader configLoader = new ConfigLoader();

    @Test
    void checkIfLoginDefaultUser() {
        loginPage.userName().sendKeys(configLoader.getUserLogin());
        loginPage.password().sendKeys(configLoader.getUserPassword());
        loginPage.button().click();

        assertEquals(configLoader.getUserFullName(), loginPage.spanWithUsersRole().getText(), "Span should contain word: Administrator ");
    }

    @BeforeEach
    public void beforeEach(){
        driver.get("http://127.0.0.1:8080/share/page/");
        loginPage = new LoginPage(driver);
    }

    @BeforeAll
    public static void beforeAll(){
        driver = configLoader.getDriver();
    }

    @AfterAll
    public static void afterAll(){
       driver.quit();
    }

}
