package it.unibo.sdwn.controller;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.Transport;

public class BaseController implements Controller, Runnable
{
    protected static Transport transport;
    protected static Routing routing;

    protected BaseController()
    {
    }

    @Override
    public synchronized Transport getTransport()
    {
        return transport;
    }

    @Override
    public synchronized void setTransport(Transport transportImpl)
    {
        transport = transportImpl;
    }

    @Override
    public synchronized Routing getRouting()
    {
        return routing;
    }

    @Override
    public synchronized void setRouting(Routing routingImpl)
    {
        routing = routingImpl;
    }

    @Subscribe
    public void init()
    {
        Thread controllerThread = new Thread(this);
        Log.main().debug("Controller thread is created and starting...");
        controllerThread.start();
    }

    @Override
    public void run()
    {
        Log.main().debug("inside");
        System.out.println("inside kir");

    }
}
