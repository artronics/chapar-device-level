package artronics.chapar.packet;

import artronics.chapar.helper.UnsignedByte;

import java.util.ArrayList;

public interface PacketFactory<P extends AbstractBasePacket,PT extends PacketType>
{
    P createPacket(PT type, PacketType.Direction direction, ArrayList<UnsignedByte> bytes);
    P createPacket(ArrayList<UnsignedByte> receivedBytes);
}
