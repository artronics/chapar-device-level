package it.unibo.sdwn.trasport;

import it.unibo.sdwn.app.config.Config;

import java.util.concurrent.TimeoutException;

public interface Connection
{
    int TIMEOUT = 2000;
    int MAX_PACKET_BUFF= Config.get().getInt("MAX_PACKET_BUFF");

    void establishConnection();
    void open();
}
