package it.unibo.sdwn.controller;

import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.Transport;

public abstract class BaseController implements Controller, Runnable
{
    protected Transport transport;
    protected Routing routing;

    public BaseController(Transport transport, Routing routing)
    {
        this.transport = transport;
        this.routing = routing;
    }

    public void init()
    {
        transport.init();
        routing.init();
    }

    @Override
    public void run()
    {
    }
}
