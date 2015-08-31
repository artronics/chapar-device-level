package artronics.chapar.protocol;

import artronics.chapar.connection.ConnectionService;
import artronics.chapar.core.events.DataInEvent;
import artronics.chapar.core.events.Event;
import artronics.chapar.core.events.PacketInEvent;
import artronics.chapar.packet.Packet;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.queue.DataInOutQueueContract;
import artronics.chapar.queue.PacketInOutQueueContract;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

final class ProtocolEngineImpl implements ProtocolEngineService
{
    private static final int MAX_DATA_LENGTH = ConnectionService.MAX_DATA_LENGTH;

    private final PacketProtocol packetProtocol;
    private final PacketFactory packetFactory;

    private final ArrayBlockingQueue<ArrayList> dataInQueue;
    private final ArrayBlockingQueue<ArrayList> dataOutQueue;
    private final ArrayBlockingQueue<Packet> packetInQueue;
    private final ArrayBlockingQueue<Packet> packetOutQueue;

    private volatile boolean isClosed = true;
    //we use this dequeue to put all integers from DataInQueue inside it
    //then packetProtocol process these stream of integers one by one.
    private final ArrayDeque<Integer> intStreamQ = new ArrayDeque<>(1024);

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

        Event.mainBus().register(this);
    }

    @Subscribe
    public void dataInEventHandler(DataInEvent event)
    {
        System.out.println("kir tush");
        while (!dataInQueue.isEmpty()) {//There is a collection of ints from connection
            try {
                ArrayList take = dataInQueue.take();
                intStreamQ.addAll(take);//Put that stream in new queue for processing by PacketProtocol
                while (!intStreamQ.isEmpty()) {//Apply protocol engine
                    while (!packetProtocol.isPacketReady()) {
                        packetProtocol.addByte(intStreamQ.pollFirst());
                        if (intStreamQ.isEmpty())
                            break;
                    }
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (packetProtocol.isPacketReady()) {
                ArrayList receivedBytes = packetProtocol.getReceivedBytes();
                Packet.Type type = packetProtocol.getType(receivedBytes);
                Packet.Direction direction = packetProtocol.getDirection(receivedBytes);
                Packet packet = packetFactory.create(type, direction, receivedBytes);
                try {
                    packetInQueue.put(packet);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                packetProtocol.clear();
                Event.mainBus().post(new PacketInEvent());
            }
        }
    }

    @Override
    public void start()
    {
        isClosed = false;

//        Thread dataReceiverThread = new Thread(new dataReceiver());
//        dataReceiverThread.start();
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
            }
        }
    }
}
