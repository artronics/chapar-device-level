package it.unibo.sdwn.trasport;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.trasport.events.TransportDataIsAvailableEvent;

public abstract class AbstractBaseTransport implements Transport, Runnable
{
    public AbstractBaseTransport()
    {
        Event.mainBus().register(this);
    }

    @Subscribe
    public void transportDataIsAvailableEventHandler(TransportDataIsAvailableEvent e)
    {
        System.out.println("kirrrrr");
    }
}
