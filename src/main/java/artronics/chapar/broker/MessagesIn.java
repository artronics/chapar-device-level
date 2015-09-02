package artronics.chapar.broker;

import artronics.chapar.core.logger.Log;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class MessagesIn implements MessagesInOut
{
    private final ArrayBlockingQueue<List> dataInQueue =
            new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    @Override
    public void put(List data)
    {
        try {
            dataInQueue.put(data);
        }catch (InterruptedException e) {
            Log.main().debug("Exp Unable to put message to dataInQueue");
            e.printStackTrace();
        }
    }

    @Override
    public List take()
    {
        List message = null;
        try {
            message = dataInQueue.take();
        }catch (InterruptedException e) {
            Log.main().debug(e.getMessage());
            //TODO you know what I mean!
        }

        return message;
    }

    @Override
    public boolean isEmpty()
    {
        return dataInQueue.isEmpty();
    }
}
