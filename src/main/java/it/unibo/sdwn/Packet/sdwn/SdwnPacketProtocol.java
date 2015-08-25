package it.unibo.sdwn.Packet.sdwn;

import it.unibo.sdwn.Packet.PacketProtocol;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;

public interface SdwnPacketProtocol extends PacketProtocol<UnsignedByte>
{
    UnsignedByte START_BYTE = UnsignedByte.of(Config.get().getByte("startByte"));
    UnsignedByte STOP_BYTE = UnsignedByte.of(Config.get().getByte("stopByte"));
    UnsignedByte DATA_PACKET_HEADER_LENGTH = UnsignedByte.of(10);

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
