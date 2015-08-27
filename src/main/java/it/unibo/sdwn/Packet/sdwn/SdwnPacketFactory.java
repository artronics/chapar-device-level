package it.unibo.sdwn.Packet.sdwn;

import it.unibo.sdwn.Packet.Packet;
import it.unibo.sdwn.Packet.PacketFactory;
import it.unibo.sdwn.Packet.protocol.sdwn.PacketProtocolHelper;
import it.unibo.sdwn.Packet.protocol.sdwn.SdwnPacketType;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;

public class SdwnPacketFactory implements PacketFactory<SdwnPacket,SdwnPacketType>
{
    @Override
    public SdwnPacket createPacket(SdwnPacketType type,Packet.Direction direction, ArrayList bytes)
    {
        return new SdwnPacket(type,direction,bytes);
    }

    public static SdwnPacket build( Packet.Direction direction,ArrayList<UnsignedByte> receivedBytes)
    {
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
