package it.unibo.sdwn.app.config;

import org.apache.commons.configuration.PropertiesConfiguration;

public class Config
{
    public void setConf(AppConfiguration conf)
    {
        this.conf = conf;
    }

    private AppConfiguration conf ;

    public Config()
    {
    }

    public PropertiesConfiguration get()
    {
        return conf.getConfig();
    }

}
