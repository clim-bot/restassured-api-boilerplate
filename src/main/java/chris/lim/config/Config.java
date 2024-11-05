package chris.lim.config;

import java.io.IOException;
import java.util.Properties;

/**
 * Config class to load environment-specific properties.
 */
public class Config {
    private static final Properties properties = new Properties();
    private static final String ENV_PROPERTY = "env";

    static {
        String env = System.getProperty(ENV_PROPERTY, "dev");
        String propertiesFile = "application-" + env + ".properties";
        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + propertiesFile, e);
        }
    }

    /**
     * Gets the value of the specified property.
     *
     * @param key the property key
     * @return the property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
