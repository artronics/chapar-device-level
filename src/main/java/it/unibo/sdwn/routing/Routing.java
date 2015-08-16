package it.unibo.sdwn.routing;

import it.unibo.sdwn.map.NetworkMap;

public interface Routing
{
    public void setNetworkMap(NetworkMap networkMapImpl);

    public void setRouting(Routing routingImpl);
}
