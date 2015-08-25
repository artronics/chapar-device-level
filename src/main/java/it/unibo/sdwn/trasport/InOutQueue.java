package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.sdwn.SdwnPacket;
import it.unibo.sdwn.app.config.Config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public interface InOutQueue
{
    int MAX_QUEUE_CAPACITY = Config.get().getInt("MAX_QUEUE_CAPACITY");
    BlockingQueue<SdwnPacket> inQueue = new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);
    BlockingQueue outQueue = new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    void putInput(SdwnPacket packet);
}

