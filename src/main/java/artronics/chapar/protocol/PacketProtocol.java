package artronics.chapar.protocol;

import artronics.chapar.packet.Packet;

import java.util.ArrayList;

public interface PacketProtocol
{
    void addByte(int receivedByte);

    boolean isPacketReady();

    void clear();

    Packet.Type getType(ArrayList receivedBytes);

    ArrayList getReceivedBytes();

    Packet.Direction getDirection(ArrayList receivedBytes);

}
