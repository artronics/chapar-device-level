package artronics.chapar.broker;

import artronics.chapar.core.logger.Log;
import artronics.chapar.packet.Packet;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class PacketsQueue implements PacketsInOut
{
    private final ArrayBlockingQueue<Packet> packetsQueue =
            new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    @Override
    public void put(Packet data)
    {
        try {
            packetsQueue.put(data);
        }catch (InterruptedException e) {
            Log.main().debug("Exp Unable to put packet to packetQueue");
            e.printStackTrace();
        }

    }

    @Override
    public Packet take()
    {
        Packet packet = null;
        try {
            packet = packetsQueue.take();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return packet;
    }

    @Override
    public boolean isEmpty()
    {
        return packetsQueue.isEmpty();
    }
}
