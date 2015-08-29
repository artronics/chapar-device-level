package artronics.chapar.controller.sdwn;

import artronics.chapar.packet.sdwn.SdwnPacket;
import artronics.chapar.packet.sdwn.SdwnPacketFactory;

import java.util.concurrent.ArrayBlockingQueue;

public class ControllerTransmitter
{

    private final ArrayBlockingQueue<SdwnPacket> outPacketQueu;
    private final SdwnPacketFactory packetFactory;

    public ControllerTransmitter(ArrayBlockingQueue<SdwnPacket> outPacketQueu, SdwnPacketFactory packetFactory)
    {
        this.outPacketQueu = outPacketQueu;
        this.packetFactory = packetFactory;
    }
    private void processOutput(SdwnPacket take)
    {
    }
}
