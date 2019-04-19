package config_loaders;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class UrlCreator {
    private Properties properties;

    public UrlCreator() {
        {
            properties = new Properties();

            try {
                InputStream inputStream = new FileInputStream("/Users/fmastalerz/IdeaProjects/alfresco/src/main/resources/environment.properties");
                properties.load(inputStream);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getURL() {
        return "http://" + properties.getProperty("hostname") + ":" + properties.getProperty("port") + properties.getProperty("pageURL");
    }
}
