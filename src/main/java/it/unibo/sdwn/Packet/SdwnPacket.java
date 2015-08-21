package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.analyser.Analysable;
import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

public class SdwnPacket implements Analysable
{
    private ArrayList<UnsignedByte> data = new ArrayList<>();

    public SdwnPacket(ArrayList<UnsignedByte> data)
    {
        this.data = data;
    }

    @Override
    public String toCsv(Object object)
    {
        return null;
    }

    public boolean is(SdwnPacket.PacketType type)
    {
        UnsignedByte typeByte = data.get(byteMeaning.TYPE.value);
        for (PacketType packetType : PacketType.values()) {
            if (typeByte.equals(packetType.value))
                return true;
        }
        return false;
    }

    public enum PacketType
    {
        DATA(new UnsignedByte(1)),
        BEACON(new UnsignedByte(2)),
        REPORT(new UnsignedByte(3)),
        RULE_REQUEST(new UnsignedByte(4)),
        RULE_RESPONSE(new UnsignedByte(5)),
        OPEN_PATH(new UnsignedByte(6));

        private UnsignedByte value;

        PacketType(UnsignedByte value)
        {
            this.value = value;
        }
    }

    private enum byteMeaning
    {
        LENGTH(0),
        NET_ID(1),
        SOURCE_L(2),
        SOURCE_H(3),
        DESTINATION_L(4),
        DESTINATION_H(5),
        TYPE(6),
        TTL(7),
        NEXT_HOP_L(8),
        NEXT_HOP_H(9),
        DISTANCE(10),
        BATTERY(11),
        NEIGHBOUR(12);

        private int value;

        byteMeaning(int value)
        {
            this.value = value;
        }
    }


}
