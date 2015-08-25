package it.unibo.sdwn.Packet;

public interface Packet
{
    PacketType getType();
    enum Direction
    {
        IN,
        OUT
    }
}
