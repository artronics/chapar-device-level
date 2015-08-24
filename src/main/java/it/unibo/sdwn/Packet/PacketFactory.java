package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.node.Address;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;

public class PacketFactory
{
    private PacketFactory()
    {
    }

    public static Packet build(ArrayList<UnsignedByte> receivedBytes, Packet.Direction direction)
    {
        SdwnPacket packet;
        try {
            //First Validate
            PacketProtocol.validate(receivedBytes);
            Packet.Type type = PacketProtocol.getType(receivedBytes);
            packet = new SdwnPacket(type, direction, receivedBytes);

        }catch (MalformedPacketException e) {
            //If any exp happens we build a malformed type packet
            //this way we can log all kind of packets and track packet lost
            packet = new SdwnPacket(Packet.Type.MALFORMED, direction, receivedBytes);
        }

        return packet;
    }

    public static DataPacket createDataFactoy(ArrayList<UnsignedByte> dataPayload,
                                              Address destNodeAddres)
    {
        DataPacket dataPacket = new DataPacket(dataPayload, destNodeAddres);
        return dataPacket;
    }
}
