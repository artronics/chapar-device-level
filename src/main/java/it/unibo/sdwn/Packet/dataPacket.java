package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.node.Address;

import java.util.ArrayList;

public class dataPacket extends SdwnPacket
{

    private UnsignedByte[] packetHeader = new UnsignedByte[10];
    private byte responseBytes[];
    private Address destNodeAddress;

    public dataPacket(ArrayList<UnsignedByte> data, int length, Address destAddress)
    {
        super(data);
        //this will make a payload of 20 bytes. see the
        //class to see how it will be initialized
        //TODO make it flexible
    }

    private void setResponseBytes()
    {
        data.add(byteMeaning.LENGTH.value, getLength());
        data.add(byteMeaning.NET_ID.value, new UnsignedByte(Config.get().getByte("NET_ID")));
        data.add(byteMeaning.SOURCE_L.value, new UnsignedByte(0));
        data.add(byteMeaning.SOURCE_H.value, new UnsignedByte(0));
        data.add(byteMeaning.DESTINATION_L.value, destNodeAddress.getAddress()[0]);
        data.add(byteMeaning.DESTINATION_H.value, destNodeAddress.getAddress()[1]);
        data.add(byteMeaning.TYPE.value, new UnsignedByte(0));
        data.add(byteMeaning.TTL.value, new UnsignedByte(Config.get().getByte("TTL")));
        data.add(byteMeaning.NEXT_HOP_L.value, new UnsignedByte(0));
        data.add(byteMeaning.NEXT_HOP_H.value, new UnsignedByte(0));

    }

//    public dataPacket(BaseNode destinationNode, Payload data) {
//        this.data = data;
//
//        byte[] response = new byte[10 + (data.length)];
//        System.arraycopy(data, 0, response, 10, data.length);
//
//        this.responseBytes = response;
//    }

}
