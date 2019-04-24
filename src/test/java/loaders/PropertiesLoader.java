package loaders;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private Properties properties;

    public Properties load(String propName) {
        properties = new Properties();

        try {
            String projectPath = System.getProperty("user.dir");
            String propertiesPath = String.format("/src/main/resources/%s.properties", propName);

            InputStream inputStream = new FileInputStream(projectPath + propertiesPath);
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }
}
