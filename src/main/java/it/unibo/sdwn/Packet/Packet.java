package it.unibo.sdwn.packet;

import it.unibo.sdwn.packet.protocol.PacketType;

public interface Packet
{
    PacketType getType();
    enum Direction
    {
        IN,
        OUT
    }
}
