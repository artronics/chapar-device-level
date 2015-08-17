package it.unibo.sdwn.routing;

import it.unibo.sdwn.map.NetworkMap;

public interface Routing
{
    public void init();

    public NetworkMap getNetworkMap();

    public void setNetworkMap(NetworkMap networkMapImpl);
}
