package automation.utils.loaders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;
import java.util.Properties;

public class EnvironmentConfigLoader {
    private Properties properties;

    public EnvironmentConfigLoader(String propName) {
        this.properties = PropertiesLoader.load(propName);
    }

    public WebDriver getDriver() {
        if (Objects.equals(properties.getProperty("browser"), "chrome")){

            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver" );
            return new ChromeDriver();
        }
        return new FirefoxDriver();
    }

    // fixme check nulls
    // fixme write buider for making URL?! do I still need this if I move urls to Pages enum?

    public String getLoginPage() {
        return properties.getProperty("loginPage");
    }

    public String getAdminToolsGroupPage() {
        return properties.getProperty("adminToolsGroupsPage");
    }

    public String getBrowseGroupsPanel() {
        return properties.getProperty("browseGroupsPanel");
    }

    public String getNewGroupPage() {
        return properties.getProperty("adminNewGroupPage");
    }

    public String getSomeGroupEditPage() {
        return properties.getProperty("someGroupEditPage");
    }

    public int getTimeOut() {
        int timeOut = Integer.parseInt(properties.getProperty("timeOut"));
        return timeOut;
    }

}
