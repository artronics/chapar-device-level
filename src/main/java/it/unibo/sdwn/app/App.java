package it.unibo.sdwn.app;

import it.unibo.sdwn.app.commandLine.CommandLineOptions;
import it.unibo.sdwn.app.config.Configuration;
import it.unibo.sdwn.app.logger.Log;


public final class App
{
    private static App instance = null;
    private static String version = "0.0";
    private static String appName = "Bologna-SDWN";
    private static Configuration config;
    private static CommandLineOptions cmd;

    private App()
    {
    }

    public static App getInstance(String[] appArgs)
    {
        if (instance == null) {
            config = new Configuration();
            cmd = cmd.getInstance(appArgs);
            instance = new App();
            return instance;
        }
        Log.instantiate("Attempt to re-instantiating singleton:", instance);

        return instance;
    }

    public static String getAppName()
    {
        return appName;
    }

    public static String getVersion()
    {
        return version;
    }

    public Configuration getConfig()
    {
        return config;
    }

    public void init()
    {
        //these options cause program to exit
        if (cmd.askedForHelp()) {
            cmd.printHelp();
            System.exit(0);
        }
        if (cmd.askedForVersion()) {
            System.out.println("0.0");
            System.exit(0);
        }
    }

}
