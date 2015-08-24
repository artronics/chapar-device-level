package it.unibo.sdwn.Packet;

import java.util.ArrayList;

public class SdwnPacket extends AbstractBasePacket
{
    public SdwnPacket(Type packetType, Direction dir, ArrayList receivedBytes)
    {
        super(packetType, dir, receivedBytes);
    }
}
