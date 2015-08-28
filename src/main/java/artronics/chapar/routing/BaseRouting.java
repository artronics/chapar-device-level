package artronics.chapar.routing;

import artronics.chapar.app.logger.Log;
import artronics.chapar.map.NetworkMap;

public abstract class BaseRouting implements Routing, Runnable
{
    protected NetworkMap networkMap;

    protected BaseRouting()
    {
    }

    @Override
    public void init()
    {
        Log.main().debug("Start Routing initializer in {}", this.getClass().getSimpleName());
//        networkMap.init();
        Log.main().debug("Router initializing done.");

    }

    //<editor-fold desc="getter and setters related to dependencies">
    @Override
    public final synchronized NetworkMap getNetworkMap()
    {
        return this.networkMap;
    }

    @Override
    public final synchronized void setNetworkMap(NetworkMap networkMapImpl)
    {
        this.networkMap = networkMapImpl;
    }

    //</editor-fold>
}
