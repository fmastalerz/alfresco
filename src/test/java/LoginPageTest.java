import config_loaders.PropertiesLoader;
import config_loaders.EnvironmentConfigLoader;
import config_loaders.UserConfigLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private static WebDriver driver;
    // I know that this is too long - will work with this tomorrow; also know that I can load properties in better way, also work with this tomorrow
    private static EnvironmentConfigLoader environmentConfigLoader = new EnvironmentConfigLoader(new PropertiesLoader().load("environment"));
    private UserConfigLoader userConfigLoader = new UserConfigLoader(new PropertiesLoader().load("user"));

    @Test
    void checkIfLoginDefaultUser() {
        loginPage.typeUsername(userConfigLoader.getUserLogin());
        loginPage.typePassword(userConfigLoader.getUserPassword());
        homePage = loginPage.submitLogin();

        String userFullName = userConfigLoader.getUserFullName();
        String spanWithUsername = homePage.nameFromSpan();
        String errorMsg = String.format("Span should contain: %s", userConfigLoader.getUserFullName());

        assertEquals(userFullName, spanWithUsername, errorMsg);
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
