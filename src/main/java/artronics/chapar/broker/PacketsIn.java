package artronics.chapar.broker;

import artronics.chapar.core.events.Event;
import artronics.chapar.core.events.PacketInEvent;
import artronics.chapar.core.logger.Log;
import artronics.chapar.packet.Packet;

import java.util.concurrent.ArrayBlockingQueue;

public class PacketsIn extends PacketsQueue
{
    private static class PacketsInLoader
    {
        private static final PacketsIn INSTANCE = new PacketsIn();
    }

    private PacketsIn()
    {
        Event.mainBus().register(this);
        if (PacketsInLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static PacketsIn getQueue()
    {
        return PacketsInLoader.INSTANCE;
    }

    @Override
    public void put(Packet data)
    {
        super.put(data);

        Log.event().debug("Firing PacketInEvent");
        Event.mainBus().post(new PacketInEvent());
    }
}
