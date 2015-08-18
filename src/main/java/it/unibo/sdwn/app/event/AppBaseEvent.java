package it.unibo.sdwn.app.event;

import java.util.stream.Stream;

public abstract class AppBaseEvent
{
    private Object data;
    private Stream stream;
    private String message;
    private Class dataType;

    public AppBaseEvent(Object data, Class dataType)
    {
        this.data = data;
        this.dataType = dataType;
    }

    public AppBaseEvent()
    {
    }

    public AppBaseEvent(Object data)
    {
        this.data = data;
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
