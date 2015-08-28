package artronics.chapar.trasport.connection;

import artronics.chapar.app.event.Event;
import artronics.chapar.trasport.events.ConnectionDataAvailableEvent;

public abstract class AbstractBaseConnection implements Connection
{
    public AbstractBaseConnection()
    {
        Event.mainBus().register(this);
    }

    protected abstract void fireConnectionDataAvailable(ConnectionDataAvailableEvent event);
}
