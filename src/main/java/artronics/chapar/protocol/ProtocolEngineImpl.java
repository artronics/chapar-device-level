package artronics.chapar.protocol;

import artronics.chapar.connection.ConnectionService;
import artronics.chapar.packet.BasePacket;
import artronics.chapar.packet.Packet;
import artronics.chapar.packet.PacketContract;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.queue.DataInOutQueueContract;
import artronics.chapar.queue.PacketInOutQueueContract;
import com.sun.tools.javac.code.Type;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class ProtocolEngineImpl implements ProtocolEngineService
{
    private static final int MAX_DATA_LENGTH = ConnectionService.MAX_DATA_LENGTH;

    private final PacketProtocol packetProtocol;
    private final PacketFactory packetFactory;

    private final ArrayBlockingQueue<ArrayList> dataInQueue;
    private final ArrayBlockingQueue<ArrayList> dataOutQueue;
    private final ArrayBlockingQueue<PacketContract> packetInQueue;
    private final ArrayBlockingQueue<PacketContract> packetOutQueue;

    private volatile boolean isClosed = true;

    public ProtocolEngineImpl(PacketProtocol packetProtocol,
                              PacketFactory packetFactory,
                              DataInOutQueueContract dataQueue,
                              PacketInOutQueueContract packetQueue)
    {
        this.packetProtocol = packetProtocol;
        this.packetFactory = packetFactory;
        this.dataInQueue = dataQueue.getDataInQueue();
        this.dataOutQueue = dataQueue.getDataOutQueue();
        this.packetInQueue = packetQueue.getPacketInQueue();
        this.packetOutQueue = packetQueue.getPacketOutQueue();
    }

    @Override
    public void start()
    {
        isClosed = false;

        Thread dataReceiverThread = new Thread(new dataReceiver());
        dataReceiverThread.start();
    }

    @Override
    public void close()
    {
        isClosed = true;
    }

    private class dataReceiver implements Runnable
    {
        //TODO refactor this peice of crap!
        @Override
        public void run()
        {
            while (!isClosed) {
                final ArrayDeque<Integer> intStreamQ = new ArrayDeque<>(1024);
                while (!dataInQueue.isEmpty()) {//There is a collection of ints from connection
                    try {
                        intStreamQ.addAll(dataInQueue.take());//Put that stream in another local queue
                        while (intStreamQ.isEmpty()) {//Apply protocol engine
                            while (!packetProtocol.isPacketReady()) {
                                packetProtocol.addByte(intStreamQ.pollFirst());
                                if (intStreamQ.isEmpty())
                                    break;
                            }
                        }
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (packetProtocol.isPacketReady()) {
                    ArrayList receivedBytes = packetProtocol.getReceivedBytes();
                    Packet.Type type = packetProtocol.getType(receivedBytes);
                    Packet.Direction direction = packetProtocol.getDirection(receivedBytes);
                    PacketContract packet = packetFactory.create(type, direction, receivedBytes);
                    try {
                        packetInQueue.put(packet);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    packetProtocol.clear();
                }
            }
        }
    }
}
