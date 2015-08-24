package it.unibo.sdwn.trasport;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.Packet.PacketProtocol;
import it.unibo.sdwn.Packet.SdwnPacketProtocol;
import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.events.ConnectionDataAvailableEvent;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;
import it.unibo.sdwn.trasport.exceptions.PacketNotReadyException;

import java.util.ArrayList;

public abstract class AbstractBaseTransport implements Transport, Runnable
{
    protected Connection connection;
    private InOutQueue packetQueue;

    public AbstractBaseTransport(InOutQueue packetQueue, Connection connection)
    {
        this.connection = connection;
        this.packetQueue = packetQueue;
        Event.mainBus().register(this);
    }

    @Subscribe
    public void connectionDataAvailableEventHandler(ConnectionDataAvailableEvent e)
    {
        ArrayList<UnsignedByte> packet = new ArrayList<>();
        //at this poit we have a buffer with fixed length
        //first we need to convert byte to unsignedByte
        UnsignedByte[] bytes = UnsignedByte.toUnsignedByteArray(e.getBuff(),e.getLength());
        //Now we need PacketProtocol to generate Sdwn packet
        //When you use SdwnPacketProtocol constructor it doesn't do too much
        //only checks if packet is corrupted or not so we can log it.
        try {
            PacketProtocol packetProtocol = new SdwnPacketProtocol(bytes);
            packet = packetProtocol.getPacket();

        }catch (MalformedPacketException e1) {
            Log.packet().error("corrupted packet is received.");
        }catch (PacketNotReadyException e1) {
            Log.packet().error("corrupted packet is received.");
        }

        packetQueue.addInput(packet);
    }
}
