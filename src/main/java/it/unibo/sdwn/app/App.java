package it.unibo.sdwn.app;

import it.unibo.sdwn.app.commandLine.CommandLineOptionsManager;
import it.unibo.sdwn.app.event.RegisterHandler;
import it.unibo.sdwn.controller.Controller;

import java.io.Serializable;


public final class App implements Serializable
{

    //Resolving dependencies by Dependency Injection
    //See DependencyInjection.xml
    private Controller controller;

    public App()
    {
    }

    public final synchronized Controller getController()
    {
        return this.controller;
    }

    public void setController(Controller controllerImpl)
    {
        this.controller = controllerImpl;
    }

    public void initCommandLine(String[] appArgs)
    {
        CommandLineOptionsManager cmd = new CommandLineOptionsManager(appArgs);
        cmd.start();
    }

    public void initEventBus()
    {
        //register all event handlers
        RegisterHandler.registerAll();
    }

    public void start()
    {
        this.controller.init();
    }
}
