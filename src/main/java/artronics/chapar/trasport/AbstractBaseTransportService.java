package artronics.chapar.trasport;

import artronics.chapar.PacketQueue.PacketQueue;
import artronics.chapar.app.event.Event;
import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.AbstractBasePacket;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.packet.PacketType;
import artronics.chapar.packet.protocol.PacketProtocol;
import artronics.chapar.trasport.connection.Connection;
import artronics.chapar.trasport.events.ConnectionDataOutAvailableEvent;

import java.util.ArrayList;
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

        PacketQueue packetQueue = new PacketQueue();
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
        //this receives bytes from connection layer
        Thread receiver = new Thread(new receiver(), "Receiver");
        receiver.start();

        Thread transmitter = new Thread(new transmitter(),"Transmitter");
        transmitter.start();
    }

    @Override
    public void close()
    {
        isClosed = true;
        connection.close();
    }


    private class transmitter implements Runnable{
        @Override
        public void run()
        {
            while (!isClosed){
                while (!outPacketQueue.isEmpty()){
                    P packet= null;
                    try {
                        packet = outPacketQueue.take();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        //in case of exp:
                        //TODO put sth to uBytes and send to sink
                        //who cares! ;-)
                    }
                    ArrayList<UnsignedByte> uBytes= new ArrayList<>(packet.getPacketBytes());
                    byte[] bytes = UnsignedByte.toByteArray(uBytes);
                    int length = bytes.length;
                    ConnectionDataOutAvailableEvent event= new
                            ConnectionDataOutAvailableEvent(this,bytes,length);
                    Event.mainBus().post(event);
                }
            }
        }
    }
    private class receiver implements Runnable
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
