package it.unibo.sdwn.controller;

import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.TransportService;

public abstract class BaseController implements Controller, Runnable
{
    protected TransportService transport;
    protected Routing routing;

    public BaseController(TransportService transport, Routing routing)
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
