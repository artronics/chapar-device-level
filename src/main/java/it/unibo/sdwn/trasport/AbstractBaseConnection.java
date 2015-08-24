package it.unibo.sdwn.trasport;

import it.unibo.sdwn.trasport.events.ConnectionDataAvailableEvent;

public abstract class AbstractBaseConnection implements Connection
{
    protected abstract void fireConnectionDataAvailable(ConnectionDataAvailableEvent event);
}
