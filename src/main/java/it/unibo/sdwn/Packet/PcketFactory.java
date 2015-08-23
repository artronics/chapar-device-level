package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.node.Address;

import java.util.ArrayList;

public class PcketFactory
{
    public static DataPacket createDataFactoy(ArrayList<UnsignedByte> dataPayload,
                                              Address destNodeAddres)
    {
        DataPacket dataPacket = new DataPacket(dataPayload, destNodeAddres);
        return dataPacket;
    }
}
