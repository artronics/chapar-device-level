package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.FakePacketFactory;
import it.unibo.sdwn.Packet.Packet;
import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.trasport.events.ConnectionDataAvailableEvent;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.ArrayList;

public class ConnectionSimulator extends AbstractBaseConnection
{
    private boolean isClosed = true;

    @Override
    protected void fireConnectionDataAvailable(ConnectionDataAvailableEvent event)
    {
        Event.mainBus().post(event);
    }

    @Override
    public void establishConnection()
    {
        //Here you need to establish your connection
        //In this simulator there is no need for this
        Log.main().info("Connection Simulator: Connection is established.");
    }

    @Override
    public void open()
    {
        //When you establish your connection you need to open
        //this connection. In this simulator we'll execute both
        //transmitter and receiver.
        isClosed = false;

    }

    @Override
    public void close()
    {
        isClosed = true;

    }

    private class Transmitter implements Runnable
    {

        @Override
        public void run()
        {
            if (isClosed) {
                throw new InvalidStateException("You have to open a connection before using it");
            }
            while (!isClosed) {
                byte[] buff = new byte[255];

                ArrayList receivedBytes = FakePacketFactory.buildGoodPacket(Packet.Type.DATA);
                ConnectionDataAvailableEvent event =
                        new ConnectionDataAvailableEvent(this, buff, buff.length);
                fireConnectionDataAvailable(event);

                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
