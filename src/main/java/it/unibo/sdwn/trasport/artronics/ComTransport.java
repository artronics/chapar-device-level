package it.unibo.sdwn.trasport.artronics;

import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.trasport.BaseTransport;

public class ComTransport extends BaseTransport
{
    @Override
    public void run()
    {

    }

    @Override
    public void init()
    {
        Log.main().debug("Start Transport layer initializer in {}", this.getClass().getSimpleName());

        Log.main().debug("Transport initializing done.");
    }
}
