import config_loaders.EnvironmentConfigLoader;
import config_loaders.UserConfigLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {
    private LoginPage loginPage;
    private static WebDriver driver;
    private static EnvironmentConfigLoader environmentConfigLoader = new EnvironmentConfigLoader();
    private UserConfigLoader userConfigLoader = new UserConfigLoader();

    @Test
    void checkIfLoginDefaultUser() {
        loginPage.userName().sendKeys(userConfigLoader.getUserLogin());
        loginPage.password().sendKeys(userConfigLoader.getUserPassword());
        loginPage.button().click();

        assertEquals(userConfigLoader.getUserFullName(), loginPage.spanWithUsersRole().getText(), "Span should contain word: " + userConfigLoader.getUserFullName());
    }

    @BeforeEach
    public void beforeEach(){
        driver.get(environmentConfigLoader.getURL());
        loginPage = new LoginPage(driver);
    }

    @BeforeAll
    public static void beforeAll(){
        driver = environmentConfigLoader.getDriver();
    }

    @AfterAll
    public static void afterAll(){
       driver.quit();
    }

}
