package artronics.chapar.connection;

import artronics.chapar.core.configuration.Config;

public interface Connection
{
    int TIMEOUT = 2000;
    int MAX_PACKET_BUFF = Config.get().getInt("MAX_PACKET_BUFF");

    void establishConnection();

    void open();

    void close();

}
