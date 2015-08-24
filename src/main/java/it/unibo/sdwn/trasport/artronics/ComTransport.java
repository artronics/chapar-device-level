package it.unibo.sdwn.trasport.artronics;

import gnu.io.SerialPort;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.trasport.BaseTransport;
import it.unibo.sdwn.trasport.Connection;
import it.unibo.sdwn.trasport.InOutPacketQueue;

public class ComTransport extends BaseTransport
{
    private Connection connection;

    public ComTransport(Connection connection)
    {
        this.connection = connection;
    }

    private InOutPacketQueue queue;
    @Override
    public void run()
    {

    }

    @Override
    public void init()
    {
        //Make a new connection. this simple object holds all you need.
        //Danger: you are about to run crappy code. Hold your breath!
//        ComConnection connection = new ComConnection();
//        //Well it's ok now!
//        this.connection = connection;
//        this.connection.open();
//        this.queue = new InOutPacketQueue();

        ComConnection comConnection = new ComConnection();
        comConnection.establishConnection();
        comConnection.open();
        Log.main().info("Transport layer initialized in {}", this.getClass().getSimpleName());
    }
}
