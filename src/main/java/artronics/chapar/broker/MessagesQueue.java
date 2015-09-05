package artronics.chapar.broker;

import artronics.chapar.core.events.Event;
import artronics.chapar.core.logger.Log;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class MessagesQueue implements MessagesInOut
{
    public MessagesQueue()
    {
        Event.mainBus().register(this);
    }

    private final ArrayBlockingQueue<List> messagesQueue =
            new ArrayBlockingQueue(MAX_QUEUE_CAPACITY);

    @Override
    public void put(List data)
    {
        Log.event().debug("Putting message to Queue");
        try {
            messagesQueue.put(data);
        }catch (InterruptedException e) {
            Log.main().debug("Exp Unable to put message to MessageQueue");
            e.printStackTrace();
        }
    }

    @Override
    public List take()
    {
        List message = null;
        Log.event().debug("Taking message from Queue");
        try {
            message = messagesQueue.take();
        }catch (InterruptedException e) {
            Log.main().debug(e.getMessage());
            //TODO you know what I mean!
        }

        return message;
    }

    @Override
    public boolean isEmpty()
    {
        return messagesQueue.isEmpty();
    }

}
