package artronics.chapar.trasport.connection;

import artronics.chapar.app.config.Config;
import artronics.chapar.trasport.events.SinkFoundEvent;

public interface Connection
{
    int TIMEOUT = 2000;
    int MAX_PACKET_BUFF = Config.get().getInt("MAX_PACKET_BUFF");

    void establishConnection();

    void fireSinkFoundEvent(SinkFoundEvent event);

    void open();

    void close();
}
