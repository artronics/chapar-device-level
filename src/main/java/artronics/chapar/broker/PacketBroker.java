package artronics.chapar.broker;

import artronics.chapar.core.events.MessageInEvent;
import artronics.chapar.core.events.Event;
import artronics.chapar.core.events.PacketInEvent;
import artronics.chapar.core.logger.Log;
import artronics.chapar.packet.Packet;
import com.google.common.eventbus.Subscribe;

import java.util.List;

public class PacketBroker
{
    private final MessageToPacketConvertorI convertor;
    private final MessagesInOut inputMsg;
    private final PacketsInOut packetsIn;

    public PacketBroker(MessageToPacketConvertorI convertor, MessagesInOut messagesIn, PacketsInOut packetsIn)
    {
        this.convertor = convertor;
        this.inputMsg = messagesIn;
        this.packetsIn = packetsIn;

        Event.mainBus().register(this);
    }


    @Subscribe
    public void messageInEventHandler(MessageInEvent event)
    {
        Log.event().debug("Handling MessageInEvent");
//        System.out.println("kir tush");
        while (!inputMsg.isEmpty()) {
            try {
                List<Integer> message = inputMsg.take();
                final List<Packet> packets = convertor.generatePackets(message);
                for (Packet packet : packets) {
                    //                System.out.println("fooo");
                    Log.event().debug("Putting packet to paketsIn-PacketBroker");
                    packetsIn.put(packet);
//                    Event.mainBus().post(new PacketInEvent());
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
