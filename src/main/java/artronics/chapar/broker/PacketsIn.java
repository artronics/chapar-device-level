package artronics.chapar.broker;

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
        if (PacketsInLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static PacketsIn getQueue()
    {
        return PacketsInLoader.INSTANCE;
    }

}
