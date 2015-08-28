package it.unibo.sdwn.packet.protocol;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

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
