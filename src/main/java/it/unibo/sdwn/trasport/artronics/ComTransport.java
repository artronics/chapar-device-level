package it.unibo.sdwn.trasport.artronics;

import gnu.io.SerialPort;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.trasport.BaseTransport;
import it.unibo.sdwn.trasport.Connection;

public class ComTransport extends BaseTransport
{
    private Connection<SerialPort> connection;
    @Override
    public void run()
    {

    }

    @Override
    public void init()
    {
        Log.main().info("Start Transport layer initializer in {}", this.getClass().getSimpleName());
        //Make a new connection. this simple object holds all you need.
        //Danger: you are about to run crappy code. Hold your breath!
        ComConnection connection = new ComConnection();
        //Well it's ok now!
        this.connection = connection;
    }

    @Override
    public Connection getConnection()
    {
        return this.connection;
    }
}
