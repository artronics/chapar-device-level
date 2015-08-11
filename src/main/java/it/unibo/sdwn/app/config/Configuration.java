package it.unibo.sdwn.app.config;

import java.io.InputStream;
import java.nio.file.Path;

public final class Configuration
{

    public ConfigProperties getAppConfig()
    {
        ConfigProperties properties = new ConfigProperties();
        this.loadConfigFile(PathConfig.APP_CONFIG_FILE, properties);

        return properties;
    }

    private ConfigProperties loadConfigFile(Path configFile, ConfigProperties properties)
    {
        InputStream inputStream;
        try {
            inputStream = getClass().getResourceAsStream(configFile.toString());
            properties.load(inputStream);
            inputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }

    /*
        This class overrides getProperty method.
        If value is null it throws an exception
     */
    public class ConfigProperties extends java.util.Properties
    {
        @Override
        public String getProperty(String key)
        {
            String value = super.getProperty(key);
            if (value == null) {
                throw new NullPointerException("The property " + "\"" + key + "\"" + " doesn't exist.");
            }

            return value;
        }
    }
}

