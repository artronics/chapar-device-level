package artronics.chapar.connection;

import artronics.chapar.core.configuration.Config;

public interface Connection
{
    int TIMEOUT = 2000;
    int MAX_DATA_QUEUE_CAPACITY = Config.get().getInt("MAX_DATA_QUEUE_CAPACITY");

    void establishConnection();

    void open();

    void close();

}
