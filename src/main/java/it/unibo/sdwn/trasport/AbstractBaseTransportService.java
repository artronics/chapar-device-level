package it.unibo.sdwn.trasport;

import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.packet.AbstractBasePacket;
import it.unibo.sdwn.packet.PacketFactory;
import it.unibo.sdwn.packet.protocol.PacketProtocol;
import it.unibo.sdwn.packet.protocol.PacketType;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class AbstractBaseTransportService
        <P extends AbstractBasePacket, PT extends PacketType, T> implements TransportService, Runnable
{
    protected Connection connection;
    protected InOutQueue packetQueue;
    protected PacketFactory<P, PT> packetFactory;
    protected P receivedPacket;
    protected ArrayBlockingQueue<T> receivedBytesQueue = new ArrayBlockingQueue<T>(1024);
    private PacketProtocol<T> packetProtocol;
    protected boolean isClosed = true;
    protected Object lock = new Object();

    public AbstractBaseTransportService(InOutQueue packetQueue,
                                        Connection connection,
                                        PacketProtocol<T> packetProtocol,
                                        PacketFactory packetFactory)
    {
        this.packetQueue = packetQueue;
        this.connection = connection;
        this.packetProtocol = packetProtocol;
        this.packetFactory = packetFactory;
        Event.mainBus().register(this);
    }

    protected class ProtocolEngine implements Runnable
    {

        @Override
        public void run()
        {
            while (!isClosed){
                synchronized (lock) {
                    while (!receivedBytesQueue.isEmpty()){



                    }
                }
            }
        }
    }
    @Override
    public void init()
    {
        connection.establishConnection();
        connection.open();
        isClosed = false;
    }

    @Override
    public void shutdown()
    {
        isClosed = true;
        connection.close();
    }
}
