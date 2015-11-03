package artronics.chapar.core.config;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Config
{
    private static boolean isLoaded = false;
    private static Set<String> props = new HashSet<String>();
    private static Properties properties;

    private Config()
    {
    }

    public static String get(ConfigType configType){

        if (!isLoaded){
            ConfigLoader configLoader = new ConfigLoader("config/chapar-config.properties");
            properties = configLoader.getProperties();
            isLoaded = true;
        }

        String result= properties.get(configType.name()).toString();

        if (result != null) {
            return result;
        }

        throw new NullPointerException();
    }

}
