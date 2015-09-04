package artronics.chapar.broker;

import artronics.chapar.core.events.DataInEvent;
import artronics.chapar.core.events.Event;
import artronics.chapar.core.events.PacketInEvent;
import artronics.chapar.packet.Packet;
import com.google.common.eventbus.Subscribe;

import java.util.List;

public class PacketBroker
{
    private final MessageToPacketConvertorI convertor;
    private final MessagesInOut inputMsg;
    private final PacketsInOut packetsIn;

    public PacketBroker(MessageToPacketConvertorI convertor, MessagesInOut inputMsg, PacketsInOut packetsIn)
    {
        this.convertor = convertor;
        this.inputMsg = inputMsg;
        this.packetsIn = packetsIn;

        Event.mainBus().register(this);
    }


    @Subscribe
    public void dataInEventHandler(DataInEvent event)
    {
        System.out.println("kir tush");
        while (!inputMsg.isEmpty()) {
            List<Integer> message = inputMsg.take();
            final List<Packet> packets = convertor.generatePackets(message);
            for (Packet packet : packets) {
                System.out.println("fooo");
                packetsIn.put(packet);
                Event.mainBus().post(new PacketInEvent());
            }
        }
    }
}
