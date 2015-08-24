package it.unibo.sdwn.trasport.artronics;

import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.trasport.AbstractBaseTransport;
import it.unibo.sdwn.trasport.Connection;
import it.unibo.sdwn.trasport.InOutPacketQueue;

public class ComTransport extends AbstractBaseTransport
{
    private Connection connection;
    private InOutPacketQueue queue;

    public ComTransport(Connection connection)
    {
        super();
        this.connection = connection;
    }

    @Override
    public void run()
    {

    }

    @Override
    public void init()
    {
        ComConnection comConnection = new ComConnection();
        comConnection.establishConnection();
        comConnection.open();
        Log.main().info("Transport layer initialized in {}", this.getClass().getSimpleName());
    }
}
