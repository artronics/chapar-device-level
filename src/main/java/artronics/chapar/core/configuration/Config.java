package artronics.chapar.core.configuration;

import org.apache.commons.configuration.PropertiesConfiguration;

public class Config
{
    private static final ChaparConfiguration conf = new ChaparConfiguration();

    private Config()
    {
    }
    public static PropertiesConfiguration get()
    {
        return conf.getConfig();
    }

}
