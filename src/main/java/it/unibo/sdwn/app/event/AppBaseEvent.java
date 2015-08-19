package it.unibo.sdwn.app.event;

import java.util.stream.Stream;

public abstract class AppBaseEvent
{
    /**
     * The object on which the Event initially occurred.
     */
    protected transient Object source;

    private Object data;
    private Stream stream;
    private String message;
    private Class dataType;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public AppBaseEvent(Object source)
    {
        if (source == null)
            throw new IllegalArgumentException("null source");

        this.source = source;
    }

    public AppBaseEvent()
    {
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

    public Stream getStream()
    {

        return stream;
    }

    public void setStream(Stream stream)
    {
        this.stream = stream;
    }

    public Class getDataType()
    {

        return dataType;
    }

    public void setDataType(Class dataType)
    {
        this.dataType = dataType;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
