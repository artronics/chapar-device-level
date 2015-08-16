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
    public synchronized NetworkMap getNetworkMap()
    {
        return networkMap;
    }

    @Override
    public synchronized void setNetworkMap(NetworkMap networkMapImpl)
    {
        networkMap = networkMapImpl;
    }

    @Override
    public synchronized void setRouting(Routing routingImpl)
    {
        routing = routingImpl;
    }
}
