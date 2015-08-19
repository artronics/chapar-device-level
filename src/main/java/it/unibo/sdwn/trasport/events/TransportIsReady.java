package it.unibo.sdwn.trasport.events;

public class TransportIsReady extends TransportEvent
{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TransportIsReady(Object source)
    {
        super(source);
    }
}
