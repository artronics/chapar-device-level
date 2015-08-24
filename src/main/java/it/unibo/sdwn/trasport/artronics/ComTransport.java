package it.unibo.sdwn.trasport.artronics;

import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.trasport.AbstractBaseTransport;
import it.unibo.sdwn.trasport.Connection;
import it.unibo.sdwn.trasport.InOutQueue;

public class ComTransport extends AbstractBaseTransport
{
    //resolv with DI
    public ComTransport(InOutQueue packetQueue, Connection connection)
    {
        super(packetQueue, connection);
    }


    @Override
    public void run()
    {

    }

    @Override
    public void init()
    {
        connection.establishConnection();
        connection.open();
        Log.main().info("Transport layer initialized in {}", this.getClass().getSimpleName());
    }
}
