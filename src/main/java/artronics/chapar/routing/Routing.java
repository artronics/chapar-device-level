package artronics.chapar.routing;

import artronics.chapar.map.NetworkMap;

public interface Routing
{
    public void init();

    public NetworkMap getNetworkMap();

    public void setNetworkMap(NetworkMap networkMapImpl);
}
