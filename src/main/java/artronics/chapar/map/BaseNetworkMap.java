package artronics.chapar.map;

import artronics.chapar.app.logger.Log;

public abstract class BaseNetworkMap implements NetworkMap, Runnable
{
    protected BaseNetworkMap()
    {
    }

    @Override
    public void run()
    {

    }

    @Override
    public void init()
    {
        Log.main().debug("Start NetworkMap initializer in {}", this.getClass().getSimpleName());

        Log.main().debug("NetworkMap initializing done.");
    }
}
