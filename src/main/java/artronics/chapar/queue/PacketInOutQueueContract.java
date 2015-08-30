package artronics.chapar.queue;

import artronics.chapar.packet.PacketContract;

import java.util.concurrent.ArrayBlockingQueue;

public interface PacketInOutQueueContract extends InOutQueue
{
    ArrayBlockingQueue getPacketInQueue();

    ArrayBlockingQueue getPacketOutQueue();
}
