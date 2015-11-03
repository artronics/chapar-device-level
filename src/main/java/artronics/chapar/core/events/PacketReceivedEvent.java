package artronics.chapar.core.events;

import java.util.List;

public class PacketReceivedEvent extends BaseEvent
{
    public PacketReceivedEvent(List<Integer> packet)
    {
        super(packet);
    }
}
