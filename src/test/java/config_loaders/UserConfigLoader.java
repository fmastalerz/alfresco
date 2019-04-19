package config_loaders;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class UserConfigLoader {
    private Properties properties;

    public UserConfigLoader() {
        properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream("/Users/fmastalerz/IdeaProjects/alfresco/src/main/resources/user.properties");
            properties.load(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUserLogin(){
        return properties.getProperty("login");
    }

    public String getUserPassword(){
        return properties.getProperty("password");
    }

    public String getUserFullName() {
        return properties.getProperty("full_name");
    }
}