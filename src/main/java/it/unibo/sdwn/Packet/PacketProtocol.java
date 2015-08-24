package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;

public interface PacketProtocol
{
    void addByte(UnsignedByte receivedByte) throws MalformedPacketException;
    boolean isReady();

    ArrayList<UnsignedByte> getPacket() throws MalformedPacketException;

    void validateReceivedBytes(ArrayList<UnsignedByte> receivedBytes) throws MalformedPacketException;
}
