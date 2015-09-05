package artronics.chapar.broker;

import artronics.chapar.core.events.Event;
import artronics.chapar.core.events.PacketOutEvent;
import artronics.chapar.core.logger.Log;
import artronics.chapar.packet.Packet;

public class PacketsOut extends PacketsQueue
{

    private static class PacketsOutLoader
    {
        private static final PacketsOut INSTANCE = new PacketsOut();
    }

    private PacketsOut()
    {
        Event.mainBus().register(this);
        if (PacketsOutLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static PacketsOut getQueue()
    {
        return PacketsOutLoader.INSTANCE;
    }

    @Override
    public void put(Packet data)
    {
        super.put(data);

        Log.event().debug("Firing PacketOutEvent");
        Event.mainBus().post(new PacketOutEvent());
    }
}
