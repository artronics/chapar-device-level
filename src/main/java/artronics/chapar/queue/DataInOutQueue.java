package artronics.chapar.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DataInOutQueue implements DataInOutQueueContract
{
    private static final int MAX_QUEUE_CAPACITY = 20;

    private static final ArrayBlockingQueue<int[]> dataInQueue = new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);
    private static final ArrayBlockingQueue<int[]> dataOutQueue = new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    @Override
    public ArrayBlockingQueue getDataInQueue()
    {
        return dataInQueue;
    }

    @Override
    public ArrayBlockingQueue getDataOutQueue()
    {
        return dataOutQueue;
    }
}
