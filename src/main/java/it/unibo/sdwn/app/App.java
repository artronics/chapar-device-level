package it.unibo.sdwn.app;

import it.unibo.sdwn.app.commandLine.CommandLineOptions;
import it.unibo.sdwn.app.config.Configuration;
import it.unibo.sdwn.app.config.DependencyInjection;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.controller.Controller;
import it.unibo.sdwn.map.Map;
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
    private Configuration configuration;
    private Controller controller;
    private Map map;
    private Routing routing;
    private Transport transport;

    //Resolving dependencies by Dependency Injection
    //See DependencyInjection class in configuration package to change implementations
    @Autowired
    public App(Controller controller, Map map, Routing routing, Transport transport)
    {
        this.controller = controller;
        this.map = map;
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
        instance.map = context.getBean(Map.class);

        Configuration conf = new Configuration();
        instance.configuration = conf;
    }

    private CommandLineOptions getCommandLineOptions(String[] args){
        CommandLineOptions cmd = CommandLineOptions.getInstance(args);

        return cmd;
    }
    public static String getAppName()
    {
        return appName;
    }

    public static String getVersion()
    {
        return version;
    }

    private void setConfiguration(Configuration configuration)
    {
        instance.configuration = configuration;
    }

    private void setController(Controller controller)
    {
        instance.controller = controller;
    }

    private void setMap(Map map)
    {
        instance.map = map;
    }

    private void setRouting(Routing routing)
    {
        instance.routing = routing;
    }

    private void setTransport(Transport transport)
    {
        instance.transport = transport;
    }

    public Configuration getConfig()
    {
        return configuration;
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
    }

}
