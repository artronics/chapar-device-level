package artronics.chapar.trasport.events;

import artronics.chapar.app.event.AppBaseEvent;
import sun.applet.AppletEvent;

public class ConnectionDataOutAvailableEvent extends ConnectionDataAvailable
{

    public ConnectionDataOutAvailableEvent(Object source, byte[] buff, int length)
    {
        super(source, buff, length);
    }
}
