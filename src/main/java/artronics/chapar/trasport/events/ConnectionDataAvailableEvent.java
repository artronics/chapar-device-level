package artronics.chapar.trasport.events;

import artronics.chapar.app.event.AppBaseEvent;

/*
    This event should fire when you get data in transport layer.
    This is the lowest level (ie. hardware).
    For example in case of Serial Comm
    when data is available on buffer you should fire this event.
 */
public class ConnectionDataAvailableEvent extends AppBaseEvent
{
    private final byte[] buff;
    private final int length;

    public ConnectionDataAvailableEvent(Object source, byte[] buff, int length)
    {
        super(source);
        this.buff = buff;
        this.length = length;
    }

    public byte[] getBuff()
    {
        return buff;
    }

    public int getLength()
    {
        return length;
    }
}
