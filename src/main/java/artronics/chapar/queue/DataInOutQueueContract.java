package artronics.chapar.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public interface DataInOutQueueContract
{
    ArrayBlockingQueue getDataInQueue();

    ArrayBlockingQueue getDataOutQueue();
}
