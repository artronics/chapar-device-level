package it.unibo.sdwn.trasport;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.Packet.PacketFactory;
import it.unibo.sdwn.Packet.sdwn.SdwnPacket;
import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.events.ConnectionDataAvailableEvent;

import java.util.ArrayList;

public final class SdwnTransport implements Transport, Runnable
{
    private Connection connection;
    private InOutQueue packetQueue;

    public SdwnTransport(InOutQueue packetQueue, Connection connection)
    {
        this.connection = connection;
        this.packetQueue = packetQueue;
        Event.mainBus().register(this);
    }

    /**
     * This handler will handle data that Connection Layer provides. There would be two situations, first Connection
     * Layer provides data byte by byte, second Connection Layer provides an Stream of Bytes. If Stream is considered
     * you just need to put it to PacketQueue, otherwise you need to instantiate a PacketProtocolHelper implementation to deal
     * with each received byte.
     *
     * @param e
     */
    @Subscribe
    public void connectionDataAvailableEventHandler(ConnectionDataAvailableEvent e)
    {
        //At this time we just deal with second situation. Because Serial Com gives us all bytes at once.
        //TODO [Feature] add code for dealing with byte by byte situation.

        //at this point we have a buffer with fixed length
        //first we need to convert byte to unsignedByte
        final int length = e.getLength();
        final byte[] buff = e.getBuff();
        ArrayList<UnsignedByte> unsignedBytes = UnsignedByte.toUnsignedByteArrayList(buff, length);
        ArrayList<UnsignedByte> receivedBytes = new ArrayList<>(unsignedBytes);

        //When you're done with creating an ArrayList of a packet we can ask
        // PacketFactory to generate a packet for us.
        SdwnPacket packet = PacketFactory.build(receivedBytes, SdwnPacket.Direction.IN);
        packetQueue.putInput(packet);
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
    }
}
