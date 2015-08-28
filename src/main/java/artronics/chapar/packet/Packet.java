package artronics.chapar.packet;

import artronics.chapar.packet.protocol.PacketType;

public interface Packet
{
    PacketType getType();
    enum Direction
    {
        IN,
        OUT
    }
}
