package artronics.chapar.trasport;

import artronics.chapar.packet.protocol.sdwn.FakeSdwnPacketFactory;
import artronics.chapar.trasport.events.SinkFoundEvent;
import artronics.chapar.app.config.Config;
import artronics.chapar.app.event.Event;
import artronics.chapar.app.logger.Log;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketType;
import artronics.chapar.trasport.events.ConnectionDataAvailableEvent;
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
    public void fireSinkFoundEvent(SinkFoundEvent event)
    {
        Event.mainBus().post(event);
    }

    @Override
    public void open()
    {
        //When you establish your connection you need to open
        //this connection. In this simulator we'll execute both
        //transmitter and receiver.
        isClosed = false;
        //before starting transmitter we have to fire
        //SinkFoundEvent
        fireSinkFoundEvent(new SinkFoundEvent(this));
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
