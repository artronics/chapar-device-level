package artronics.chapar.PacketQueue;

import artronics.chapar.app.config.Config;
import artronics.chapar.packet.AbstractBasePacket;

import java.util.concurrent.ArrayBlockingQueue;

public class PacketQueue<P extends AbstractBasePacket>
{
    private static final int MAX_QUEUE_CAPACITY = Config.get().getInt("MAX_QUEUE_CAPACITY");
    private static final ArrayBlockingQueue inPacketQueue = new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);
    private static final ArrayBlockingQueue outPacketQueue = new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    public static ArrayBlockingQueue getInPacketQueue()
    {
        return inPacketQueue;
    }

    public static ArrayBlockingQueue getOutPacketQueue()
    {
        return outPacketQueue;
    }
}
