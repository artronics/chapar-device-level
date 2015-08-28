package artronics.chapar.PacketQueue;

import artronics.chapar.app.config.Config;
import artronics.chapar.app.event.Event;
import artronics.chapar.packet.AbstractBasePacket;

import java.util.concurrent.ArrayBlockingQueue;

public abstract class AbstractBasePacketQueue<P extends AbstractBasePacket> implements PacketQueue<P>
{
    int MAX_QUEUE_CAPACITY = Config.get().getInt("MAX_QUEUE_CAPACITY");
    protected final ArrayBlockingQueue<P> inQueue = new ArrayBlockingQueue<>(MAX_QUEUE_CAPACITY);
    protected final ArrayBlockingQueue<P> outQueue= new ArrayBlockingQueue<>(MAX_QUEUE_CAPACITY);

    public AbstractBasePacketQueue()
    {
        Event.mainBus().register(this);
    }
}
