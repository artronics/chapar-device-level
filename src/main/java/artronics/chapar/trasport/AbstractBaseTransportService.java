package artronics.chapar.trasport;

import artronics.chapar.PacketQueue.PacketQueue;
import artronics.chapar.app.event.Event;
import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.AbstractBasePacket;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.packet.PacketType;
import artronics.chapar.packet.protocol.PacketProtocol;

import java.util.concurrent.ArrayBlockingQueue;

public abstract class AbstractBaseTransportService
        <P extends AbstractBasePacket, PT extends PacketType> implements TransportService, Runnable
{
    protected Connection connection;
    protected PacketQueue<P> packetQueue;
    protected PacketFactory<P, PT> packetFactory;
    protected ArrayBlockingQueue<UnsignedByte> receivedBytesQueue = new ArrayBlockingQueue(256);
    protected boolean isClosed = true;
    protected Object lock = new Object();
    private PacketProtocol<PT> packetProtocol;

    public AbstractBaseTransportService(PacketQueue<P> packetQueue,
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
