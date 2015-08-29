package artronics.chapar.trasport.connection;

import artronics.chapar.app.event.Event;
import artronics.chapar.trasport.events.ConnectionDataInAvailableEvent;
import artronics.chapar.trasport.events.ConnectionDataOutAvailableEvent;
import com.google.common.eventbus.Subscribe;

public abstract class AbstractBaseConnection implements Connection
{
    public AbstractBaseConnection()
    {
        Event.mainBus().register(this);
    }

    protected abstract void fireConnectionDataInAvailableEvent(ConnectionDataInAvailableEvent event);
    @Subscribe
    public abstract void connectionDataOutAvailableEventHandler(ConnectionDataOutAvailableEvent event);
}
