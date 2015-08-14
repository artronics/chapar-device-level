package it.unibo.sdwn.app.config;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

final class Configuration
{

    private static Map configurations;

    public Configuration()
    {
        this.configurations = getAllConfigurations();
    }

    public static String get(ConfigType configType)
    {
        String key = configType.toString();
        return configurations.get(key).toString();
    }

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

    private Map getAllConfigurations()
    {
        int length = ConfigType.values().length;
        Map<String, String> configs = new HashMap<>();
        for (int i = 0; i < length; ++i) {
            String configKey = ConfigType.values()[i].toString();
            String configValue = this.getAppConfig().getProperty(configKey.toString());
            configs.put(configKey, configValue);
        }

        return configs;
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

