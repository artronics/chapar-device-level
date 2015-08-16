package it.unibo.sdwn.routing;

import it.unibo.sdwn.map.NetworkMap;

public abstract class BaseRouting implements Routing, Runnable
{
    protected static NetworkMap networkMap;
    protected static Routing routing;

    protected BaseRouting()
    {
    }

    //<editor-fold desc="getter and setters related to dependencies">
    @Override
    public final synchronized NetworkMap getNetworkMap()
    {
        return networkMap;
    }

    @Override
    public final synchronized void setNetworkMap(NetworkMap networkMapImpl)
    {
        networkMap = networkMapImpl;
    }

    @Override
    public final synchronized void setRouting(Routing routingImpl)
    {
        routing = routingImpl;
    }
    //</editor-fold>
}
