package artronics.chapar.broker;

import artronics.chapar.core.configuration.Config;
import artronics.chapar.packet.Packet;

public interface PacketsInOut
{
    int MAX_QUEUE_CAPACITY = Config.get().getInt("MAX_QUEUE_CAPACITY");

    void put(Packet data);

    Packet take();

    boolean isEmpty();
}
