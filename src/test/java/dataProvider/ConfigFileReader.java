package dataProvider;

import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigFileReader {

    private static final String PROPERTY_FILE_PATH = "configs"+ File.separator + "Configuration.properties";

    private final Properties configProperties;

    private static ConfigFileReader instance;

    public static ConfigFileReader getInstance(){
        return instance == null ? instance = new ConfigFileReader() : instance;
    }

    private ConfigFileReader(){
        try{
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(PROPERTY_FILE_PATH))){
                configProperties = new Properties();
                configProperties.load(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + PROPERTY_FILE_PATH);
        }
    }

    private String readProperty(String property){
        String propertyValue= configProperties.getProperty(property);
        if(Strings.isNullOrEmpty(propertyValue)){
            throw new RuntimeException(property + " not specified in the Configuration.properties file.");
        }
        else{
            return propertyValue;
        }
    }

    public String getOpenTriviaDBUrl() {
        return readProperty("openTriviaDB_url");
    }

}
