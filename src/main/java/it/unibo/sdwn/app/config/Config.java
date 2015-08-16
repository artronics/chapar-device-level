package it.unibo.sdwn.app.config;

import org.apache.commons.configuration.PropertiesConfiguration;

public class Config
{
    private static boolean isInstanciated = false;
    private static PropertiesConfiguration propertiesConfiguration =new PropertiesConfiguration();

    private Config()
    {
    }

    synchronized public static PropertiesConfiguration get()
    {
        AppConfiguration conf = null;
        if (!isInstanciated) {
            conf = new AppConfiguration();
            propertiesConfiguration = conf.getConfig();
            isInstanciated = true;
        }

        return propertiesConfiguration;
    }

}
