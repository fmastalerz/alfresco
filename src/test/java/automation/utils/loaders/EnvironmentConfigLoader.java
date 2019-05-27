package automation.utils.loaders;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;
import java.util.Properties;

public class EnvironmentConfigLoader {
    private PropertiesConfiguration properties;

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
        return properties.getString("loginPage");
    }

    public String getAdminToolsGroupPage() {
        return properties.getString("adminToolsGroupsPage");
    }

    public String getBrowseGroupsPanel() {
        return properties.getString("browseGroupsPanel");
    }

    public String getNewGroupPage() {
        return properties.getString("adminNewGroupPage");
    }

    public String getSomeGroupEditPage() {
        return properties.getString("someGroupEditPage");
    }

    public int getTimeOut() {
        return properties.getInt("timeOut");
    }

}
