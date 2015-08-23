package it.unibo.sdwn.trasport.events;

import it.unibo.sdwn.app.event.AppBaseEvent;

/*
    This event should fire when you get data in transport layer.
    This is the lowest level (ie. hardware).
    For example in case of Serial Comm
    when data is available on buffer you should fire this event.
 */
public class TransportDataIsAvailableEvent extends AppBaseEvent
{
    public TransportDataIsAvailableEvent(Object source, Object data)
    {
        super(source, data);
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TransportDataIsAvailableEvent(Object source)
    {
        super(source);
    }
}
