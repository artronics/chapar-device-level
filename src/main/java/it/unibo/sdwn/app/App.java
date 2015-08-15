package it.unibo.sdwn.app;

import it.unibo.sdwn.app.commandLine.CommandLineOptions;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.config.DependencyInjection;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.controller.Controller;
import it.unibo.sdwn.map.NetworkMap;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public final class App
{
    private static App instance = null;
    private static String version = "0.0";
    private static String appName = "Bologna-SDWN";
    //Main dependencies
    private CommandLineOptions commandLineOptions;
    private Controller controller;
    private NetworkMap networkMap;
    private Routing routing;
    private Transport transport;

    //Resolving dependencies by Dependency Injection
    //See DependencyInjection class in configuration package to change implementations
    @Autowired
    public App(Controller controller, NetworkMap networkMap, Routing routing, Transport transport)
    {
        this.controller = controller;
        this.networkMap = networkMap;
        this.routing = routing;
        this.transport = transport;
    }
    public App()
    {
    }

    public static App getInstance(String[] appArgs)
    {
        if (instance == null) {
            App app = new App();
            instance = app;
            getDependecies();
            instance.commandLineOptions = instance.getCommandLineOptions(appArgs);

            return instance;
        }
        Log.instantiate("Attempt to re-instantiating singleton:", instance);

        return instance;
    }

    private static void getDependecies()
    {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(DependencyInjection.class);
        instance.controller = context.getBean(Controller.class);
        instance.routing = context.getBean(Routing.class);
        instance.transport = context.getBean(Transport.class);
        instance.networkMap = context.getBean(NetworkMap.class);

    }

    public static String getAppName()
    {
        return appName;
    }

    public static String getVersion()
    {
        return version;
    }

    public void runGui()
    {
        final boolean hasGui = Config.get().getBoolean("hasGui");
        if (hasGui) {

        }
    }

    private CommandLineOptions getCommandLineOptions(String[] args)
    {
        CommandLineOptions cmd = CommandLineOptions.getInstance(args);

        return cmd;
    }

    private void setController(Controller controller)
    {
        instance.controller = controller;
    }

    private void setNetworkMap(NetworkMap networkMap)
    {
        instance.networkMap = networkMap;
    }

    private void setRouting(Routing routing)
    {
        instance.routing = routing;
    }

    private void setTransport(Transport transport)
    {
        instance.transport = transport;
    }

    public void init()
    {
        //these options cause program to exit
        if (commandLineOptions.askedForHelp()) {
            commandLineOptions.printHelp();
            System.exit(0);
        }
        if (commandLineOptions.askedForVersion()) {
            System.out.println("0.0");
            System.exit(0);
        }
        //Run gui if availible
        runGui();
    }

}
