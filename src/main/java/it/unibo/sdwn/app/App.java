package it.unibo.sdwn.app;

import it.unibo.sdwn.app.commandLine.CommandLineOptionsManager;
import it.unibo.sdwn.app.event.RegisterHandler;
import it.unibo.sdwn.controller.Controller;


public final class App
{

    //Main dependencies
    private Controller controller;

    public App()
    {
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

    private void loadDependencies()
    {
        DependencyBuilder dependencyBuilder = new DependencyBuilder();
        dependencyBuilder.start();
        this.controller = dependencyBuilder.getController();
    }

    public void initDependencies()
    {
        loadDependencies();
        this.controller.init();
    }
}
