package artronics.chapar.packet;

import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.protocol.PacketType;

import java.util.ArrayList;

public interface PacketFactory<P extends AbstractBasePacket,PT extends PacketType>
{
    P createPacket(PT type,Packet.Direction direction, ArrayList bytes);
    P createPacket(ArrayList<UnsignedByte> receivedBytes);
}
