package it.unibo.sdwn.trasport.events;

import it.unibo.sdwn.app.event.AppBaseEvent;

public class TransportEvent extends AppBaseEvent
{

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TransportEvent(Object source)
    {
        super(source);
    }
}
