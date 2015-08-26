package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.sdwn.FakeSdwnPacketFactory;
import it.unibo.sdwn.Packet.sdwn.SdwnPacketType;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.trasport.events.ConnectionDataAvailableEvent;
import sun.plugin.dom.exception.InvalidStateException;

public class ConnectionSimulator extends AbstractBaseConnection
{
    private static final long TRANSMITTER_PERION = Config.get().getLong("transmitterPeriod");
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
        Log.main().debug("Connection Simulator: Connection is established.");
    }

    @Override
    public void open()
    {
        //When you establish your connection you need to open
        //this connection. In this simulator we'll execute both
        //transmitter and receiver.
        isClosed = false;
        Thread transmitter = new Thread(new Transmitter(), "Transmitter");
        Log.main().debug("Start Transmitter thread.");
        transmitter.start();

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
                byte[] receivedBytes = FakeSdwnPacketFactory.buildGoodByteArray(SdwnPacketType.BEACON);
                //Simulate inputStream.read
                for (int i = 0; i < receivedBytes.length; i++) {
                    buff[i] = receivedBytes[i];
                }
                int length = receivedBytes.length;
                ConnectionDataAvailableEvent event =
                        new ConnectionDataAvailableEvent(this, buff, length);
                fireConnectionDataAvailable(event);

                try {
                    Thread.sleep(TRANSMITTER_PERION);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
