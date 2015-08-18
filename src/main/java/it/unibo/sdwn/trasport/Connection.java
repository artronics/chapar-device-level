package it.unibo.sdwn.trasport;

public interface Connection<T>
{
    public void open();

    public void close();

    public T getConnection();
}
