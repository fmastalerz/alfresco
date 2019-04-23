package config_loaders;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    Properties properties;

    public Properties load(String propName) {
        properties = new Properties();

        try {
            String projectPath = System.getProperty("user.dir");
            String propertiesPath = String.format("/src/main/resources/%s.properties", propName);

            // should I split below line for two String variables like it is now or is there better way to handle that?
            InputStream inputStream = new FileInputStream(projectPath + propertiesPath);
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }
}
