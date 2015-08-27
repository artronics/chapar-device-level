package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.AbstractBasePacket;
import it.unibo.sdwn.Packet.PacketFactory;
import it.unibo.sdwn.Packet.protocol.PacketType;
import it.unibo.sdwn.app.event.Event;

public abstract class AbstractBaseTransportService
        <P extends AbstractBasePacket, PT extends PacketType> implements TransportService, Runnable
{
    protected Connection connection;
    protected InOutQueue packetQueue;
    protected PacketFactory<P,PT> packetFactory;
    protected P receivedPacket;

    public AbstractBaseTransportService(InOutQueue packetQueue,
                                Connection connection,
                                PacketFactory packetFactory)
    {
        this.packetQueue = packetQueue;
        this.connection = connection;
        this.packetFactory = packetFactory;
        Event.mainBus().register(this);
    }
}
