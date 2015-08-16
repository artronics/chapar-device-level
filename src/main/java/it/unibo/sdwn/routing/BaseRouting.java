package it.unibo.sdwn.routing;

import it.unibo.sdwn.map.NetworkMap;

public abstract class BaseRouting implements Routing, Runnable
{
    protected static NetworkMap networkMap;
    protected static Routing routing;

    protected BaseRouting()
    {
    }

    @Override
    public void setRouting(Routing routingImpl)
    {
        routing = routingImpl;
    }

    public void setNetworkMap(NetworkMap networkMapImpl)
    {
        networkMap = networkMapImpl;
    }
}
