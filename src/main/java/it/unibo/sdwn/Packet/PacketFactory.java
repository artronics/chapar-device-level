package it.unibo.sdwn.Packet;

import it.unibo.sdwn.node.AbstractBaseAddress;

import java.util.ArrayList;
import java.util.List;

public interface PacketFactory<P extends AbstractBasePacket,PT extends PacketType>
{
    P createPacket(PT type,Packet.Direction direction, ArrayList bytes);
}
