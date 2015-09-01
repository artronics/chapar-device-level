package artronics.chapar.protocol;

import artronics.chapar.packet.Packet;

import java.util.ArrayList;

public class PacketProtocolImp implements PacketProtocol
{
    @Override
    public void addByte(int receivedByte)
    {

    }

    @Override
    public boolean isPacketReady()
    {
        return false;
    }

    @Override
    public void clear()
    {

    }

    @Override
    public Packet.Type getType(ArrayList receivedBytes)
    {
        return null;
    }

    @Override
    public ArrayList getReceivedBytes()
    {
        return null;
    }

    @Override
    public Packet.Direction getDirection(ArrayList receivedBytes)
    {
        return null;
    }
}
