package artronics.chapar.broker;

import artronics.chapar.core.events.Event;
import artronics.chapar.core.events.MessageInEvent;
import artronics.chapar.core.logger.Log;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class MessagesIn extends MessagesQueue
{
    @Override
    public void put(List data)
    {
        super.put(data);

        Log.event().debug("Firing MessageInEvent");
        Event.mainBus().post(new MessageInEvent());
    }
}
