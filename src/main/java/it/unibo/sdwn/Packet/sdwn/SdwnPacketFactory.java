package it.unibo.sdwn.packet.sdwn;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.packet.Packet;
import it.unibo.sdwn.packet.PacketFactory;
import it.unibo.sdwn.packet.protocol.PacketProtocol;
import it.unibo.sdwn.packet.protocol.sdwn.SdwnPacketType;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;

public class SdwnPacketFactory implements PacketFactory<SdwnPacket,SdwnPacketType>
{
    private PacketProtocol<SdwnPacketType> packetProtocol;

    public SdwnPacketFactory(PacketProtocol packetProtocol)
    {
        this.packetProtocol = packetProtocol;
    }

    @Override
    public SdwnPacket createPacket(ArrayList<UnsignedByte> receivedBytes)
    {
        SdwnPacket packet;
        if(packetProtocol.isValid(receivedBytes)){
            SdwnPacketType type = packetProtocol.getType(receivedBytes);
            packet = new SdwnPacket(type, Packet.Direction.IN,receivedBytes);

            return packet;
        }
        packet = new SdwnPacket(SdwnPacketType.MALFORMED, Packet.Direction.IN,receivedBytes);

        return packet;
    }

    @Override
    public SdwnPacket createPacket(SdwnPacketType type,Packet.Direction direction, ArrayList bytes)
    {
        return new SdwnPacket(type,direction,bytes);
    }
}
