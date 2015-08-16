package it.unibo.sdwn.app.config;

import org.apache.commons.configuration.PropertiesConfiguration;

public class Config
{
    private static AppConfiguration conf = null;
    private static PropertiesConfiguration propertiesConfiguration =new PropertiesConfiguration();

    private Config()
    {
    }

    synchronized public static PropertiesConfiguration get()
    {
        if (conf == null) {
            conf = new AppConfiguration();
            propertiesConfiguration = conf.getConfig();
        }

        return propertiesConfiguration;
    }

}
