package it.unibo.sdwn.Packet;

import it.unibo.sdwn.Packet.sdwn.PacketProtocolHelper;
import it.unibo.sdwn.Packet.sdwn.SdwnBasePacket;
import it.unibo.sdwn.Packet.sdwn.SdwnPacket;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;

public class PacketFactory
{
    private PacketFactory()
    {
    }

    public static SdwnPacket build(ArrayList<UnsignedByte> receivedBytes, SdwnPacket.Direction direction)
    {
        SdwnBasePacket packet;
        try {
            //First Validate
            PacketProtocolHelper.validate(receivedBytes);
            SdwnPacket.Type type = PacketProtocolHelper.getType(receivedBytes);
            packet = new SdwnBasePacket(type, direction, receivedBytes);

        }catch (MalformedPacketException e) {
            //If any exp happens we build a malformed type packet
            //this way we can log all kind of packets and track packet lost
            packet = new SdwnBasePacket(SdwnPacket.Type.MALFORMED, direction, receivedBytes);
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
