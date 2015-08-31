package artronics.chapar.queue;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

final class DataInOutQueue implements DataInOutQueueContract
{

    private static final ArrayBlockingQueue<ArrayList> dataInQueue =
            new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    private static final ArrayBlockingQueue<ArrayList> dataOutQueue =
            new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

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
