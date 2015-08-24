package it.unibo.sdwn.trasport;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public interface InOutQueue
{
    public static final int MAX_QUEUE_CAPACITY = Config.get().getInt("MAX_QUEUE_CAPACITY");
    BlockingQueue<ArrayList<UnsignedByte>> inQueue = new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);
    BlockingQueue outQueue = new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    void addInput(ArrayList<UnsignedByte> packet);
}

