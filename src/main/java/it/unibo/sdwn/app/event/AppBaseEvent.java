package it.unibo.sdwn.app.event;

/**
 * The type App base event.
 */
public abstract class AppBaseEvent
{
    private final Object data;
    private final String message;
    /**
     * The object on which the Event initially occurred.
     */
    protected transient Object source;


    /**
     * Instantiates a new App base event.
     *
     * @param source the source
     */
    public AppBaseEvent(Object source)
    {
//        if (source == null)
//            throw new IllegalArgumentException("null source");
//
        this.source = source;
        this.data = null;
        this.message = null;
    }


    /**
     * Instantiates a new App base event.
     *
     * @param source the source
     * @param data   the data
     */
    public AppBaseEvent(Object source, Object data)
    {
        this.source = source;
        this.data = data;
        message = null;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @param data the data
     * @param message the message
     * @throws IllegalArgumentException if source is null.
     */
    public AppBaseEvent(Object source, Object data, String message)
    {

        this.source = source;
        this.data = data;
        this.message = message;
    }

    /**
     * The object on which the Event initially occurred.
     *
     * @return The object on which the Event initially occurred.
     */
    public Object getSource()
    {
        return source;
    }

    /**
     * Returns a String representation of this EventObject.
     *
     * @return A a String representation of this EventObject.
     */
    public String toString()
    {
        return getClass().getName() + "[source=" + source + "]";
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData()
    {
        return data;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }
}
