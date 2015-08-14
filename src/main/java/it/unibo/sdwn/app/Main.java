package it.unibo.sdwn.app;

import it.unibo.sdwn.app.config.Config;

public class Main
{
    public static void main(String[] args)
    {
        App app = App.getInstance(args);
        app.init();

        System.out.println(Config.get().getString("COM_PORT"));
        System.out.println(Config.get().getString("comPort"));

        System.out.println(Config.get().getInt("INT_KEY"));
    }
}
