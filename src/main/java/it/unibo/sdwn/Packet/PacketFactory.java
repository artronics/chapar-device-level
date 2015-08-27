package it.unibo.sdwn.packet;

import it.unibo.sdwn.packet.protocol.PacketType;

import java.util.ArrayList;

public interface PacketFactory<P extends AbstractBasePacket,PT extends PacketType>
{
    P createPacket(PT type,Packet.Direction direction, ArrayList bytes);
    P createPacket(ArrayList receivedBytes);
}
