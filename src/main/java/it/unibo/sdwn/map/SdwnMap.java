package it.unibo.sdwn.map;

public class SdwnMap implements NetworkMap
{
    private NetworkMap sdwnNetworkMap;
    @Override
    public NetworkMap getMap()
    {
        return this.sdwnNetworkMap;
    }
}
