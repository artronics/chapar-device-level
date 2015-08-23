package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

public interface Packet
{
    ArrayList<UnsignedByte> getBytes();

    Type getType();

    int getLength();

    enum Type
    {
        DATA(UnsignedByte.of(1)),
        BEACON(UnsignedByte.of(2)),
        REPORT(UnsignedByte.of(3)),
        RULE_REQUEST(UnsignedByte.of(4)),
        RULE_RESPONSE(UnsignedByte.of(5)),
        OPEN_PATH(UnsignedByte.of(6));

        protected UnsignedByte value;

        Type(UnsignedByte value)
        {
            if (value.intValue() > 6)
                this.value = UnsignedByte.of(6);
            else
                this.value = value;
        }
    }

    enum byteMeaning
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
        NEIGHBOUR(12),
        START_TIME_L(11),
        START_TIME_H(12),
        STOP_TIME_L(13),
        STOP_TIME_H(14),;

        protected int value;

        byteMeaning(int value)
        {
            this.value = value;
        }
    }
}
