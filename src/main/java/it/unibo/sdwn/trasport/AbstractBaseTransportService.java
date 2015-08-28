package it.unibo.sdwn.trasport;

import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.packet.AbstractBasePacket;
import it.unibo.sdwn.packet.Packet;
import it.unibo.sdwn.packet.PacketFactory;
import it.unibo.sdwn.packet.protocol.PacketProtocol;
import it.unibo.sdwn.packet.protocol.PacketType;
import it.unibo.sdwn.packet.protocol.sdwn.SdwnPacketType;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class AbstractBaseTransportService
        <P extends AbstractBasePacket, PT extends PacketType> implements TransportService, Runnable
{
    protected Connection connection;
    protected InOutQueue packetQueue;
    protected PacketFactory<P, PT> packetFactory;
    protected P receivedPacket;
    protected ArrayBlockingQueue<UnsignedByte> receivedBytesQueue = new ArrayBlockingQueue(1024);
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
    }

    @Override
    public void shutdown()
    {
        isClosed = true;
        connection.close();
    }

    protected class ProtocolEngine implements Runnable
    {
        @Override
        public void run()
        {
            ArrayList<UnsignedByte> receivedBytes = new ArrayList<>();
            receivedBytesQueue.drainTo(receivedBytes);
            for (UnsignedByte b:receivedBytes){
                System.out.print(b);
            }
            System.out.println();
//            while (!isClosed) {
//                synchronized (lock) {
//                    while (!receivedBytesQueue.isEmpty()) {
//                        while (!packetProtocol.isPacketReady()) {
//                            try {
//                                packetProtocol.addByte(receivedBytesQueue.take());
//                                if(receivedBytesQueue.isEmpty()) break;
//                            }catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            }
        }
    }
}
