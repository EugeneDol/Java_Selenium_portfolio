package utils.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ReadProperties;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

public class Config extends ReadProperties {
    private static String PROPERTIES_PATH = "src/main/resources/configs/default.properties";
    private static String CREDENTIALS_FILE = "src/main/resources/credentials.properties";

    public static String getConfigProperty(String fieldName) {
        return getProperty(PROPERTIES_PATH, fieldName);
    }



    public static boolean isDemo() {
        String env = Config.getConfigProperty("env");
        return env.contains("demo");
    }
}
