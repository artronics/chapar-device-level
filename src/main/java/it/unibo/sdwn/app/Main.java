package it.unibo.sdwn.app;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.config.ConfigType;

public class Main
{
    public static void main(String[] args)
    {
        App app = App.getInstance(args);
        app.init();
        System.out.println(Config.get(ConfigType.COM_PORT));
    }
}
