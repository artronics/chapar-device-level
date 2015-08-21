package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.SdwnPacket;
import it.unibo.sdwn.app.config.Config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public interface InOutQueue
{
    BlockingQueue inQueue = new ArrayBlockingQueue<SdwnPacket>(Config.get().getInt("MAX_QUEUE_CAPACITY"));
    BlockingQueue outQueue = new ArrayBlockingQueue<SdwnPacket>(Config.get().getInt("MAX_QUEUE_CAPACITY"));

    static BlockingQueue getOutQueue()
    {
        return outQueue;
    }

    static BlockingQueue getInQueue()
    {
        return inQueue;
    }
}
