package it.unibo.sdwn.packet.protocol;

import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;

public interface PacketProtocol<B>
{
    void addByte(B receivedByte);
    boolean isReady();
    void clear();
    ArrayList<B> getReceivedBytes();
    boolean validateReceivedBytes(ArrayList<B> receivedBytes);
}
