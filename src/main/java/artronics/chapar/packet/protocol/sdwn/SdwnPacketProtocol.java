package artronics.chapar.packet.protocol.sdwn;

import artronics.chapar.app.config.Config;
import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.protocol.PacketProtocol;

public interface SdwnPacketProtocol extends PacketProtocol<SdwnPacketType>
{
    UnsignedByte START_BYTE = UnsignedByte.of(Config.get().getByte("startByte"));
    UnsignedByte STOP_BYTE = UnsignedByte.of(Config.get().getByte("stopByte"));
    UnsignedByte DATA_PACKET_HEADER_LENGTH = UnsignedByte.of(10);

    enum ByteMeaning
    {
        START_BYTE(0),
        LENGTH(1),
        NET_ID(2),
        //TODO Which one is Low and which one is high?
        SOURCE_H(3),
        SOURCE_L(4),
        DESTINATION_H(5),
        DESTINATION_L(6),
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

        public int getValue()
        {
            return value;
        }
    }
}
