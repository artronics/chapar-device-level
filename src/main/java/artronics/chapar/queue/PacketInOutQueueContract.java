package artronics.chapar.queue;

import java.util.concurrent.ArrayBlockingQueue;

public interface PacketInOutQueueContract extends InOutQueue
{
    ArrayBlockingQueue getPacketInQueue();

    ArrayBlockingQueue getPacketOutQueue();
}
