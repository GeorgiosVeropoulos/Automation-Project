package managers;

import exceptions.AutomationException;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class ConfigManager {


    private static final String CONFIG_FILE = System.getProperty("property.file");

    public static void loadConfigFile() {
        Properties properties = new Properties();
        try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                log.error("Error loading .properties file. Make sure property.file is set in CL");
                throw new AutomationException();
            }
            properties.load(inputStream);
            properties.forEach((key, value) -> System.setProperty((String) key, (String)value));

        } catch (IOException e) {
            log.error("Error loading .properties file. Make sure property.file is set in CL");
            throw new AutomationException(e.getMessage());
        }
    }

}
