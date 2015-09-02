package artronics.chapar.broker;

import artronics.chapar.core.events.DataInEvent;
import artronics.chapar.core.events.Event;
import artronics.chapar.packet.BasePacket;
import artronics.chapar.packet.Packet;
import com.google.common.eventbus.Subscribe;

import java.util.List;

public class MessageBroker
{
    private final MessageToPacketConvertorI convertor;
    private final MessagesInOut inputMsg;
    private final PacketsInOut packetsIn;

    public MessageBroker(MessageToPacketConvertorI convertor, MessagesInOut inputMsg, PacketsInOut packetsIn)
    {
        this.convertor = convertor;
        this.inputMsg = inputMsg;
        this.packetsIn = packetsIn;

        Event.mainBus().register(this);
    }

    @Subscribe
    public void dataInEventHandler(DataInEvent event)
    {
        try {
            System.out.println("kir tush");
            while (!inputMsg.isEmpty()) {
                List<Integer> message = inputMsg.take();
                final List<List<Integer>> packetsBytes = convertor.generateRawPackets(message);
                for (List packetBytes : packetsBytes) {
                    Packet packet = BasePacket.create(packetBytes);
                    packetsIn.put(packet);
                }
            }
        }catch (Exception e) {
            System.out.println("madareto");
            e.printStackTrace();
        }
    }
}