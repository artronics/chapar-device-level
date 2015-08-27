package it.unibo.sdwn.trasport;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.trasport.events.SinkFoundEvent;

public interface Connection
{
    int TIMEOUT = 2000;
    int MAX_PACKET_BUFF = Config.get().getInt("MAX_PACKET_BUFF");

    void establishConnection();

    void fireSinkFoundEvent(SinkFoundEvent event);

    void open();

    void close();
}
