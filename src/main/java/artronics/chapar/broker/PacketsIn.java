package artronics.chapar.broker;

import artronics.chapar.core.logger.Log;
import artronics.chapar.packet.Packet;

import java.util.concurrent.ArrayBlockingQueue;

public class PacketsIn implements PacketsInOut
{
    private final ArrayBlockingQueue<Packet> packetsInQueue =
            new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    private static class PacketsInLoader
    {
        private static final PacketsIn INSTANCE = new PacketsIn();
    }

    private PacketsIn()
    {
        if (PacketsInLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static PacketsIn getQueue()
    {
        return PacketsInLoader.INSTANCE;
    }

    @Override
    public void put(Packet data)
    {
        try {
            packetsInQueue.put(data);
        }catch (InterruptedException e) {
            Log.main().debug("Exp Unable to put packet to packetInQueue");
            e.printStackTrace();
        }
    }

    @Override
    public Packet take()
    {
        return null;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }
}
