import config_loaders.EnvironmentConfigLoader;
import config_loaders.UrlCreator;
import config_loaders.UserConfigLoader;
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
    static EnvironmentConfigLoader environmentConfigLoader = new EnvironmentConfigLoader();
    UserConfigLoader userConfigLoader = new UserConfigLoader();
    UrlCreator urlCreator = new UrlCreator();

    @Test
    void checkIfLoginDefaultUser() {
        loginPage.userName().sendKeys(userConfigLoader.getUserLogin());
        loginPage.password().sendKeys(userConfigLoader.getUserPassword());
        loginPage.button().click();

        assertEquals(userConfigLoader.getUserFullName(), loginPage.spanWithUsersRole().getText(), "Span should contain word: Administrator ");
    }

    @BeforeEach
    public void beforeEach(){
        driver.get(urlCreator.getURL());
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
