package artronics.chapar.device;

public interface Connection
{
    int TIMEOUT = 2000;

    void open();

    void close();
}
