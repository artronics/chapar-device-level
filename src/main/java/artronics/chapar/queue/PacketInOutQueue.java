package artronics.chapar.queue;

import artronics.chapar.packet.PacketContract;

import java.util.concurrent.ArrayBlockingQueue;

public class PacketInOutQueue implements PacketInOutQueueContract
{
    private static final ArrayBlockingQueue<PacketContract> packetInQueue =
            new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    private static final ArrayBlockingQueue<PacketContract> packetOutQueue =
            new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    @Override
    public ArrayBlockingQueue getPacketInQueue()
    {
        return packetInQueue;
    }

    @Override
    public ArrayBlockingQueue getPacketOutQueue()
    {
        return packetOutQueue;
    }
}
