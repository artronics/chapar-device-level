package artronics.chapar.app;

import artronics.chapar.app.commandLine.CommandLineOptionsManager;
import artronics.chapar.app.event.RegisterHandler;
import artronics.chapar.controller.ControllerService;

import java.io.Serializable;


public final class App implements Serializable
{

    //Resolving dependencies by Dependency Injection
    //See DependencyInjection.xml
    private ControllerService controller;

    private App()
    {
    }

    public final synchronized ControllerService getController()
    {
        return this.controller;
    }

    public void setController(ControllerService controllerImpl)
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
