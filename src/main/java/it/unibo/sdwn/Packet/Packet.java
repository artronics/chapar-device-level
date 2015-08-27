package it.unibo.sdwn.Packet;

import it.unibo.sdwn.Packet.protocol.PacketType;

public interface Packet
{
    PacketType getType();
    enum Direction
    {
        IN,
        OUT
    }
}
