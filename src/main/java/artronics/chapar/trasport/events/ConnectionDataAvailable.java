package artronics.chapar.trasport.events;

import artronics.chapar.app.event.AppBaseEvent;

public class ConnectionDataAvailable extends AppBaseEvent
{
    private final byte[] buff;
    private final int length;

    public ConnectionDataAvailable(Object source, byte[] buff, int length)
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
