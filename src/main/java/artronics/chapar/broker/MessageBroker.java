package artronics.chapar.broker;

import artronics.chapar.core.events.Event;
import artronics.chapar.core.events.MessageOutEvent;
import artronics.chapar.core.events.PacketOutEvent;
import artronics.chapar.core.logger.Log;
import artronics.chapar.packet.Packet;
import com.google.common.eventbus.Subscribe;

import java.util.List;

public class MessageBroker
{
    private final PacketToMessageConvertor convertor;
    private final PacketsOut packetsOut;
    private final MessagesOut messagesOut;

    public MessageBroker(PacketToMessageConvertor convertor, PacketsOut packetsOut,
                         MessagesOut messagesOut)
    {
        this.convertor = convertor;
        this.packetsOut = packetsOut;
        this.messagesOut = messagesOut;

        Event.mainBus().register(this);
    }


    @Subscribe
    public void packetOutEventHandler(PacketOutEvent event)
    {
        Log.event().debug("Handling PacketOutEvent");

        Packet packet = packetsOut.take();
        List message = convertor.convert(packet);

        messagesOut.put(message);

        Log.main().debug("Firing MessageOutEvent");
        Event.mainBus().post(new MessageOutEvent());
    }
}
