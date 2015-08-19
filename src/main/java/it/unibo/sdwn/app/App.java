package it.unibo.sdwn.app;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.app.commandLine.CommandLineOptionsManager;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.app.event.RegisterHandler;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.controller.Controller;
import it.unibo.sdwn.trasport.events.TransportIsReady;


public final class App
{
    private static App instance = null;

    //Main dependencies
    private Controller controller;

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
        return this.controller;
    }

    //Resolving dependencies by Dependency Injection
    //See DependencyInjection.xml
    public final synchronized void setController(Controller controllerImpl)
    {
        this.controller = controllerImpl;
    }

    public void init(String[] appArgs)
    {
        Event.mainBus().post(new TransportIsReady());
        Event.mainBus().post(new TransportIsReady());
        CommandLineOptionsManager cmd = new CommandLineOptionsManager(appArgs);
        cmd.start();
        //register all event handlers
        RegisterHandler.registerAll();

        loadDependencies();
        initDependencies();

        //Run gui if available
        runGui();
    }

    private void loadDependencies()
    {
        DependencyBuilder dependencyBuilder = new DependencyBuilder();
        dependencyBuilder.start();
        this.controller = dependencyBuilder.getController();

    }

    private void initDependencies()
    {
        Log.main().debug("Start initializing dependency tree...");
        this.controller.init();
        Log.main().debug("All dependencies are initialized successfully.");
    }

    private void runGui()
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
