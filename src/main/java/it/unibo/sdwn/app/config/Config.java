package it.unibo.sdwn.app.config;

import org.apache.commons.configuration.PropertiesConfiguration;

public class Config
{
    private static AppConfiguration conf = new AppConfiguration();

    private Config()
    {
    }

    public static PropertiesConfiguration get()
    {
        return conf.getConfig();
    }

}
