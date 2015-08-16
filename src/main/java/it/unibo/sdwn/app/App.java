package it.unibo.sdwn.app;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.app.commandLine.CommandLineOptionsManager;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.event.RegisterHandler;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.controller.Controller;
import it.unibo.sdwn.trasport.events.TransportIsReady;


public final class App
{
    private static App instance = null;

    //Main dependencies
    private static Controller controller;

    private App()
    {
    }

    public static App getInstance()
    {
        if (instance == null) {
            App app = new App();
            instance = app;

            return instance;
        }

        return instance;
    }

    public final synchronized Controller getController()
    {
        return controller;
    }

    //Resolving dependencies by Dependency Injection
    //See DependencyInjection.xml
    public final synchronized void setController(Controller controllerImpl)
    {
        controller = controllerImpl;
    }

    public void init(String[] appArgs)
    {
        CommandLineOptionsManager cmd = new CommandLineOptionsManager(appArgs);
        cmd.start();
        //register all event handlers
        RegisterHandler.registerAll();

        //Run gui if available
        runGui();
    }

    public void runGui()
    {
        final boolean hasGui = Config.get().getBoolean("hasGui");
        if (hasGui) {

        }
    }


    @Subscribe
    public void transportIsready(TransportIsReady e)
    {
        Log.main().debug("trans is ready handler");
    }


}
