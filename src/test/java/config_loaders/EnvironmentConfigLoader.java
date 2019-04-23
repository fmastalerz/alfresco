package config_loaders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class EnvironmentConfigLoader {
    private Properties properties;

    public EnvironmentConfigLoader(Properties properties) {
        this.properties = properties;
    }

    public WebDriver getDriver() {
        if (properties.getProperty("browser").equals("chrome")){
            return new ChromeDriver();
        }
        // I choose to return firefox driver as a default one that's why I don't use switch-case
        return new FirefoxDriver();
    }

    public String getURL() {
        String hostname = properties.getProperty("hostname");
        String portNmb = properties.getProperty("port");
        String pageURL = properties.getProperty("pageURL");

        return String.format("http://%s:%s%s", hostname, portNmb, pageURL );
    }
}
