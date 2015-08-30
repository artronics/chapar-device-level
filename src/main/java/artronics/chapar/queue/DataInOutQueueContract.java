package artronics.chapar.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public interface DataInOutQueueContract extends InOutQueue
{
    ArrayBlockingQueue getDataInQueue();

    ArrayBlockingQueue getDataOutQueue();
}
