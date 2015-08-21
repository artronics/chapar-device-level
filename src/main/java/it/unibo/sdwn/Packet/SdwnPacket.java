package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.analyser.Analysable;
import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

public class SdwnPacket implements Analysable
{
    private ArrayList<UnsignedByte> data = new ArrayList<>();
    private PacketType packetType;

    public SdwnPacket(ArrayList<UnsignedByte> data)
    {
        this.data = data;
        //Todo it must have a minimum length
        UnsignedByte typeByte = this.data.get(byteMeaning.TYPE.value);
        for (PacketType packetType : PacketType.values()) {
            if (typeByte.equals(packetType.value))
                this.packetType = packetType;
        }

        //see original code starter gestisci_pacchetto default
        //case for switch statement. don't forget to change the test
        //See also PacketType enum constructor
        //TODO What is the default value for Type if it wouldn't be between those values?
        if (typeByte.intValue() > 6)
            this.packetType = PacketType.OPEN_PATH;
    }

    public PacketType getPacketType()
    {
        return packetType;
    }

    @Override
    public String toCsv(Object object)
    {
        return null;
    }

    public boolean is(SdwnPacket.PacketType type)
    {
        return (this.packetType == type);
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
            if (value.intValue() > 6)
                this.value = new UnsignedByte(6);
            else
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
