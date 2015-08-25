package it.unibo.sdwn.Packet.sdwn;

import it.unibo.sdwn.Packet.AbstractBasePacket;
import it.unibo.sdwn.Packet.Packet;

import java.util.ArrayList;

public class SdwnBasePacket extends AbstractBasePacket<SdwnPacketType>
{
    public SdwnBasePacket(SdwnPacketType packetType, Packet.Direction dir, ArrayList receivedBytes)
    {
        super(packetType, dir, receivedBytes);
    }
}
