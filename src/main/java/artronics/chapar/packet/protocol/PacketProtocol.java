package artronics.chapar.packet.protocol;

import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.PacketType;

import java.util.ArrayList;

public interface PacketProtocol<PT extends PacketType>
{
    void addByte(UnsignedByte receivedByte);
    boolean isPacketReady();
    void clear();
    PT getType(ArrayList<UnsignedByte> receivedBytes);
    ArrayList<UnsignedByte> getReceivedBytes();
    boolean isValid(ArrayList<UnsignedByte> receivedBytes);
}
