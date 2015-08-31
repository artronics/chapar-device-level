package artronics.chapar.queue;

import artronics.chapar.packet.Packet;

import java.util.concurrent.ArrayBlockingQueue;

final class PacketInOutQueue implements PacketInOutQueueContract
{
    private static final ArrayBlockingQueue<Packet> packetInQueue =
            new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    private static final ArrayBlockingQueue<Packet> packetOutQueue =
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
