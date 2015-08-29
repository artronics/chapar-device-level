package artronics.chapar.trasport;

import artronics.chapar.PacketQueue.PacketQueue;
import artronics.chapar.app.event.Event;
import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.AbstractBasePacket;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.packet.PacketType;
import artronics.chapar.packet.protocol.PacketProtocol;
import artronics.chapar.trasport.connection.Connection;

import java.util.concurrent.ArrayBlockingQueue;

public abstract class AbstractBaseTransportService
        <P extends AbstractBasePacket, PT extends PacketType>
        implements TransportService
{
    protected final Connection connection;
    protected final PacketFactory<P, PT> packetFactory;
    protected final ArrayBlockingQueue<UnsignedByte> receivedBytesQueue = new ArrayBlockingQueue(256);
    protected final Object lock = new Object();
    protected final ArrayBlockingQueue<P> inPacketQueue;
    protected final ArrayBlockingQueue<P> outPacketQueue;
    private final PacketProtocol<PT> packetProtocol;
    protected boolean isClosed = true;

    public AbstractBaseTransportService(Connection connection,
                                        PacketProtocol<PT> packetProtocol,
                                        PacketFactory packetFactory)
    {
        this.connection = connection;
        this.packetProtocol = packetProtocol;
        this.packetFactory = packetFactory;

        PacketQueue<P> packetQueue = new PacketQueue<>();
        this.inPacketQueue = packetQueue.getInPacketQueue();
        this.outPacketQueue = packetQueue.getOutPacketQueue();

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
                        P packet = packetFactory.createPacket(packetProtocol.getReceivedBytes());
                        //put this packet to PacketQueue's input
                        //TODO Do I need to synch this call?
                        try {
                            inPacketQueue.put(packet);
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //clear packetProtocol so be ready for next packet
                        packetProtocol.clear();
                    }
                }
            }
        }
    }
}
