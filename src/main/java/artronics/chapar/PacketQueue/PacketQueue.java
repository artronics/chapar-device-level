package artronics.chapar.PacketQueue;

import artronics.chapar.app.config.Config;
import artronics.chapar.packet.AbstractBasePacket;

import java.util.concurrent.ArrayBlockingQueue;

//I know this design sucks but I couldn't figure out a better way!
//I couldn't resolve these queues with DI in constructors.

/**
 * Holds two Queues and input and output for incoming and outgoing type {@link P} packets. <p>This class holds two
 * references to <code>ArrayBlockingQueue</code> so both {@link artronics.chapar.controller.ControllerService} and
 * {@link artronics.chapar.trasport.TransportService} can use them.</P> <p>Each service uses getters to maintain a
 * reference to both provided queues.</p>
 *
 * @param <P>
 */
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
