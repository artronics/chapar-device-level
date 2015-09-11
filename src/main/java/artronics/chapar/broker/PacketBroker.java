package artronics.chapar.broker;

import artronics.chapar.core.events.MessageInEvent;
import artronics.chapar.core.events.Event;
import artronics.chapar.core.logger.Log;
import artronics.chapar.packet.Packet;
import com.google.common.eventbus.Subscribe;

import java.util.List;

public class PacketBroker
{
    private final MessageToPacketConvertorI convertor;
    private final MessagesInOut messagesIn;
    private final PacketsInOut packetsIn;

    public PacketBroker(MessageToPacketConvertorI convertor, MessagesInOut messagesIn, PacketsInOut packetsIn)
    {
        this.convertor = convertor;
        this.messagesIn = messagesIn;
        this.packetsIn = packetsIn;

        Event.mainBus().register(this);
    }


    @Subscribe
    public void messageInEventHandler(MessageInEvent event)
    {
        Log.event().debug("Handling MessageInEvent");
        while (!messagesIn.isEmpty()) {
            try {
                List<Integer> message = messagesIn.take();
                final List<Packet> packets = convertor.generatePackets(message);
                for (Packet packet : packets) {
                    Log.event().debug("Putting packet to paketsIn-PacketBroker");
                    packetsIn.put(packet);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
