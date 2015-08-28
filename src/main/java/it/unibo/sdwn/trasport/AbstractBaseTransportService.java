package it.unibo.sdwn.trasport;

import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.packet.AbstractBasePacket;
import it.unibo.sdwn.packet.PacketFactory;
import it.unibo.sdwn.packet.protocol.PacketProtocol;
import it.unibo.sdwn.packet.protocol.PacketType;

import java.util.concurrent.ArrayBlockingQueue;

public abstract class AbstractBaseTransportService
        <P extends AbstractBasePacket, PT extends PacketType> implements TransportService, Runnable
{
    protected Connection connection;
    protected InOutQueue packetQueue;
    protected PacketFactory<P, PT> packetFactory;
    protected ArrayBlockingQueue<UnsignedByte> receivedBytesQueue = new ArrayBlockingQueue(256);
    protected boolean isClosed = true;
    protected Object lock = new Object();
    private PacketProtocol<PT> packetProtocol;

    public AbstractBaseTransportService(InOutQueue packetQueue,
                                        Connection connection,
                                        PacketProtocol<PT> packetProtocol,
                                        PacketFactory packetFactory)
    {
        this.packetQueue = packetQueue;
        this.connection = connection;
        this.packetProtocol = packetProtocol;
        this.packetFactory = packetFactory;
        Event.mainBus().register(this);
    }

    @Override
    public void init()
    {
        connection.establishConnection();
        connection.open();
        isClosed = false;
        Thread protocolEngine = new Thread(new ProtocolEngine(), "ProtocolEngine");
        protocolEngine.start();
    }

    @Override
    public void close()
    {
        isClosed = true;
        connection.close();
    }

    protected class ProtocolEngine implements Runnable
    {
        @Override
        public void run()
        {
            while (!isClosed) {
                while (!receivedBytesQueue.isEmpty()) {
                    synchronized (lock) {
                        while (!packetProtocol.isPacketReady()) {
                            try {
                                packetProtocol.addByte(receivedBytesQueue.take());
                                if (receivedBytesQueue.isEmpty())
                                    break;
                            }catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (packetProtocol.isPacketReady()) {
                        packetFactory.createPacket(packetProtocol.getReceivedBytes());
                        packetProtocol.clear();
                    }
                }
            }
        }
    }
}
