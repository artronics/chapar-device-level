package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.node.Address;

import java.util.ArrayList;

public final class DataPacket extends SdwnPacket
{
    private Address destNodeAddress = new Address();
    private ArrayList<UnsignedByte> data = new ArrayList<>();
    private ArrayList<UnsignedByte> header = new ArrayList<>();

    //See PacketFactory
    protected DataPacket(ArrayList<UnsignedByte> dataPayload,
                      Address destNodeAddres)
    {
        int packetLeng = dataPayload.size() + DATA_PACKET_HEADER_LENGTH.intValue();
        UnsignedByte paketLength = UnsignedByte.of(packetLeng);

        setHeader(destNodeAddres, paketLength);
        data = new ArrayList<>(dataPayload);
        data.addAll(0, header);
    }

    protected void setHeader(Address destNodeAddress, UnsignedByte packetLength)
    {
        header.add(byteMeaning.LENGTH.value, packetLength);
        header.add(byteMeaning.NET_ID.value, UnsignedByte.of(Config.get().getByte("NET_ID")));
        header.add(byteMeaning.SOURCE_L.value, UnsignedByte.of(0));
        header.add(byteMeaning.SOURCE_H.value, UnsignedByte.of(0));
        header.add(byteMeaning.DESTINATION_L.value, destNodeAddress.getAddress()[0]);
        header.add(byteMeaning.DESTINATION_H.value, destNodeAddress.getAddress()[1]);
        header.add(byteMeaning.TYPE.value, Type.DATA.value);
        header.add(byteMeaning.TTL.value, UnsignedByte.of(Config.get().getByte("TTL")));
        header.add(byteMeaning.NEXT_HOP_L.value, UnsignedByte.of(0));
        header.add(byteMeaning.NEXT_HOP_H.value, UnsignedByte.of(0));
    }

    @Override
    public ArrayList<UnsignedByte> getBytes()
    {
        return data;
    }

    @Override
    public Type getType()
    {
        return Type.DATA;
    }

    @Override
    public int getLength()
    {
        return data.size();
    }
}
