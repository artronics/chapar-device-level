package it.unibo.sdwn.Packet.sdwn;

import it.unibo.sdwn.Packet.PacketType;
import it.unibo.sdwn.helper.UnsignedByte;

public enum SdwnPacketType implements PacketType
{
    MALFORMED(UnsignedByte.of(0)),
    DATA(UnsignedByte.of(1)),
    BEACON(UnsignedByte.of(2)),
    REPORT(UnsignedByte.of(3)),
    RULE_REQUEST(UnsignedByte.of(4)),
    RULE_RESPONSE(UnsignedByte.of(5)),
    OPEN_PATH(UnsignedByte.of(6));

    protected UnsignedByte value;

    SdwnPacketType(UnsignedByte value)
    {
        if (value.intValue() > 6)
            this.value = UnsignedByte.of(6);
        else
            this.value = value;
    }
}