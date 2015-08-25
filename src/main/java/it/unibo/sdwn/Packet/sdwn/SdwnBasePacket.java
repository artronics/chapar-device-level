package it.unibo.sdwn.Packet.sdwn;

import it.unibo.sdwn.Packet.AbstractBasePacket;

import java.util.ArrayList;

public class SdwnBasePacket extends AbstractBasePacket
{
    public SdwnBasePacket(SdwnPacket.Type packetType, SdwnPacket.Direction dir, ArrayList receivedBytes)
    {
        super(packetType, dir, receivedBytes);
    }
}
