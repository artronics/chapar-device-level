package it.unibo.sdwn.trasport;

import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.trasport.events.ConnectionDataAvailableEvent;

public abstract class AbstractBaseConnection implements Connection
{
    public AbstractBaseConnection()
    {
        Event.mainBus().register(this);
    }

    protected abstract void fireConnectionDataAvailable(ConnectionDataAvailableEvent event);
}
