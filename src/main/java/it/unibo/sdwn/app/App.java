package it.unibo.sdwn.app;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.app.commandLine.CommandLineOptions;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.event.RegisterHandler;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.controller.Controller;
import it.unibo.sdwn.trasport.ComTransport;
import it.unibo.sdwn.trasport.Transport;
import it.unibo.sdwn.trasport.events.TransportIsReady;
import org.springframework.beans.factory.annotation.Autowired;


public final class App
{
    private static App instance = null;
    private static String version = "0.0";
    private static String appName = "Bologna-SDWN";

    //Main dependencies
    private CommandLineOptions commandLineOptions;
    private Controller controller;
    private Transport transport;

    //Resolving dependencies by Dependency Injection
    //See DependencyInjection class in configuration package to change implementations
    @Autowired
    public App(Controller controller, Transport transport)
    {
        this.controller = controller;
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
//        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext(
//                "DependencyInjection.xml");
//        instance.controller = xmlContext.getBean(Controller.class);
//        instance.transport = xmlContext.getBean(Transport.class);
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


    public void init()
    {
        manageCommandLineOptions();

        System.out.println(instance.transport);
        System.out.println(instance.controller);
        RegisterHandler.registerAll();
        ComTransport comTransport = new ComTransport();


        //Run gui if availible
        runGui();

        //Run Controller
//        instance.controller.init();
    }

    @Subscribe
    public void transportIsready(TransportIsReady e)
    {
        Log.main().debug("trans is ready handler");

    }

    public Controller getController()
    {
        return instance.controller;
    }

    private void manageCommandLineOptions()
    {//these options cause program to exit
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
