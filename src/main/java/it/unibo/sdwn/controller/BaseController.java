package it.unibo.sdwn.controller;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.Transport;

public abstract class BaseController implements Controller, Runnable
{
    protected Transport transport;
    protected Routing routing;

    protected BaseController()
    {
    }


    @Subscribe
    public void init()
    {
        Log.main().debug("Start Controller initializer in {}", this.getClass().getSimpleName());
        transport.init();
        routing.init();
        Log.main().debug("Controller initializing done.");
    }

    @Override
    public void run()
    {
    }

    //<editor-fold desc="setters and getters related to Controller dependencies">
    @Override
    public final synchronized Transport getTransport()
    {
        return transport;
    }

    @Override
    public final synchronized void setTransport(Transport transportImpl)
    {
        this.transport = transportImpl;
    }

    @Override
    public final synchronized Routing getRouting()
    {
        return routing;
    }

    @Override
    public final synchronized void setRouting(Routing routingImpl)
    {
        this.routing = routingImpl;
    }
    //</editor-fold>
}
