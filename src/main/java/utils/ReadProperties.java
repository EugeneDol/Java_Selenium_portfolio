package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.config.Config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ReadProperties {
    protected static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

    protected static String getProperty(String configPath, String fieldName) {
        String result = null;

        try {
            String absoluteConfigPath = String.valueOf(Paths.get(configPath).toAbsolutePath());
            FileReader propReader = new FileReader(absoluteConfigPath);
            Properties properties = new Properties();
            properties.load(propReader);

            result = properties.getProperty(fieldName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("Config file \"" + configPath + "\" was not found");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Config \"" + configPath + "\" was not opened");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Field \"" + fieldName + "\" was not found in the \"" + configPath + "\"");
        }

        return result;
    }

}
