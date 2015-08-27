package it.unibo.sdwn.packet.sdwn;

import it.unibo.sdwn.packet.Packet;
import it.unibo.sdwn.packet.PacketFactory;
import it.unibo.sdwn.packet.protocol.sdwn.PacketProtocolHelper;
import it.unibo.sdwn.packet.protocol.sdwn.SdwnPacketType;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;

public class SdwnPacketFactory implements PacketFactory<SdwnPacket,SdwnPacketType>
{
    @Override
    public SdwnPacket createPacket(SdwnPacketType type,Packet.Direction direction, ArrayList bytes)
    {
        return new SdwnPacket(type,direction,bytes);
    }

    @Override
    public SdwnPacket createPacket(ArrayList receivedBytes)
    {
        Packet.Direction direction = Packet.Direction.IN;
        SdwnPacket packet;
        try {
            //First Validate
            PacketProtocolHelper.validate(receivedBytes);
            SdwnPacketType type = PacketProtocolHelper.getType(receivedBytes);
            packet = new SdwnPacket(type, direction, receivedBytes);

        }catch (MalformedPacketException e) {
            //If any exp happens we build a malformed type packet
            //this way we can log all kind of packets and track packet lost
            packet = new SdwnPacket(SdwnPacketType.MALFORMED, direction, receivedBytes);
        }

        return packet;

    }
//    public static DataPacket createDataFactoy(ArrayList<UnsignedByte> dataPayload,
//                                              Address destNodeAddres)
//    {
//        DataPacket dataPacket = new DataPacket(dataPayload, destNodeAddres);
//        return dataPacket;
//    }
}
