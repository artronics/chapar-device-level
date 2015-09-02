package artronics.chapar.broker;

import artronics.chapar.core.configuration.Config;

import java.util.List;

public interface MessagesInOut
{
    int MAX_QUEUE_CAPACITY = Config.get().getInt("MAX_QUEUE_CAPACITY");

    void put(List data);

    List take();

    boolean isEmpty();
}
