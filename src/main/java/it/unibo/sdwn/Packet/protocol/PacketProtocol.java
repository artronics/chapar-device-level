package it.unibo.sdwn.Packet.protocol;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;

public interface PacketProtocol<B>
{
    void addByte(B receivedByte) throws MalformedPacketException;
    boolean isReady();
    ArrayList<B> getReceivedBytes() throws MalformedPacketException;
    boolean validateReceivedBytes(ArrayList<B> receivedBytes);
}
