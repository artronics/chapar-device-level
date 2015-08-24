package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.Packet;
import it.unibo.sdwn.app.config.Config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public interface InOutQueue
{
    int MAX_QUEUE_CAPACITY = Config.get().getInt("MAX_QUEUE_CAPACITY");
    BlockingQueue<Packet> inQueue = new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);
    BlockingQueue outQueue = new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    void putInput(Packet packet);
}

