package it.unibo.sdwn.controller;

import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.Transport;

public interface Controller
{
    public Transport getTransport();

    public void setTransport(Transport transportImpl);

    public Routing getRouting();

    public void setRouting(Routing routingImpl);
    public void init();
}
