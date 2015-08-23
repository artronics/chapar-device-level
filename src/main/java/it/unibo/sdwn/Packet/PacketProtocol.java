package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;
import it.unibo.sdwn.trasport.exceptions.PacketNotReadyException;

import java.util.ArrayList;

public interface PacketProtocol
{
    void addByte(UnsignedByte receivedByte) throws MalformedPacketException;
    boolean isReady();
    ArrayList getPacket() throws PacketNotReadyException;
}
