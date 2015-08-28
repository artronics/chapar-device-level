package artronics.chapar.packet.sdwn;

import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.packet.PacketType;
import artronics.chapar.packet.protocol.PacketProtocol;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketType;

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
            packet = new SdwnPacket(type, PacketType.Direction.IN,receivedBytes);

            return packet;
        }
        packet = new SdwnPacket(SdwnPacketType.MALFORMED, PacketType.Direction.IN,receivedBytes);

        return packet;
    }

    @Override
    public SdwnPacket createPacket(SdwnPacketType type,PacketType.Direction direction, ArrayList bytes)
    {
        return new SdwnPacket(type,direction,bytes);
    }
}
