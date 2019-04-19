import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties;

    public ConfigLoader() {
        properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream("/Users/fmastalerz/IdeaProjects/alfresco/src/test/java/config.properties");
            properties.load(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        if (properties.getProperty("browser").equals("chrome")){
            return new ChromeDriver();
        }
        if  (properties.getProperty("browser").equals("firefox")){
            return new FirefoxDriver();
        }
        else {
            //what if I got no browser in properties? how should I deal with it?
            return null;
        }
    }

    public String getUserLogin(){
        return properties.getProperty("user");
    }

    public String getUserPassword(){
        return properties.getProperty("password");
    }

    public String getUserFullName() {
        return properties.getProperty("full_name");
    }
}
