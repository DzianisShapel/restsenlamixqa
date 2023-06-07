package eu.senla.lab.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    public ConfigLoader() throws IOException {
        InputStream input = Files.newInputStream(Paths.get("src/main/resources/config.properties"));
        properties = new Properties();
        properties.load(input);
    }

    public static ConfigLoader getInstance() throws IOException {
        if(configLoader == null ){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId(){
        String prop = properties.getProperty("client_id");
        if(prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file: ");
    }

    public String getClientSecret(){
        String prop = properties.getProperty("client_secret");
        if(prop != null) return prop;
        else throw new RuntimeException("property client_secret is not specified in the config.properties file: ");
    }

    public String getGrantType(){
        String prop = properties.getProperty("grant_type");
        if(prop != null) return prop;
        else throw new RuntimeException("property grant_type is not specified in the config.properties file: ");
    }

    public String getEmail(){
        String prop = properties.getProperty("email");
        if(prop != null) return prop;
        else throw new RuntimeException("property email is not specified in the config.properties file: ");
    }

    public String getPassword(){
        String prop = properties.getProperty("password");
        if(prop != null) return prop;
        else throw new RuntimeException("property email is not specified in the config.properties file: ");
    }
}
