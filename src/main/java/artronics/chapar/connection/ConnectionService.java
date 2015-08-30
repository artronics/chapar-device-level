package artronics.chapar.connection;

import artronics.chapar.core.configuration.Config;

public interface ConnectionService
{
    int TIMEOUT = 2000;
    int MAX_DATA_LENGTH = Config.get().getInt("MAX_DATA_LENGTH");

    void establishConnection();

    void open();

    void close();

}
