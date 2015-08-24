package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.analyser.Analysable;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;

public interface Packet extends Analysable
{
    UnsignedByte START_BYTE = UnsignedByte.of(Config.get().getByte("startByte"));
    UnsignedByte STOP_BYTE = UnsignedByte.of(Config.get().getByte("stopByte"));
    UnsignedByte DATA_PACKET_HEADER_LENGTH = UnsignedByte.of(10);

    enum Type
    {
        MALFORMED(UnsignedByte.of(0)),
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

    enum Direction
    {
        IN,
        OUT
    }

    enum ByteMeaning
    {
        START_BYTE(0),
        LENGTH(1),
        NET_ID(2),
        SOURCE_L(3),
        SOURCE_H(4),
        DESTINATION_L(5),
        DESTINATION_H(6),
        TYPE(7),
        TTL(8),
        NEXT_HOP_L(9),
        NEXT_HOP_H(10),
        DISTANCE(11),
        BATTERY(12),
        NEIGHBOUR(13),
        START_TIME_L(12),
        START_TIME_H(13),
        STOP_TIME_L(14),
        STOP_TIME_H(15),;

        protected int value;

        ByteMeaning(int value)
        {
            this.value = value;
        }
    }
}
