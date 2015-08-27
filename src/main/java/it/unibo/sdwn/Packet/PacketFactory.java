package it.unibo.sdwn.Packet;

import it.unibo.sdwn.Packet.protocol.PacketType;

import java.util.ArrayList;

public interface PacketFactory<P extends AbstractBasePacket,PT extends PacketType>
{
    P createPacket(PT type,Packet.Direction direction, ArrayList bytes);
}
