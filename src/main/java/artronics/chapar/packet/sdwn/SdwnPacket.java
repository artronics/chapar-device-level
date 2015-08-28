package artronics.chapar.packet.sdwn;

import artronics.chapar.packet.AbstractBasePacket;
import artronics.chapar.packet.PacketType;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketType;

import java.util.ArrayList;

public class SdwnPacket extends AbstractBasePacket<SdwnPacketType>
{

    /**
     * This id the minimum requirement for constructing a packet. We need packet.Type and the Direction of packet and
     * also
     * received bytes. Then logger will log the constructed packet which might be even malformed packet.
     *
     * @param packetType
     * @param dir
     * @param receivedBytes
     */
    protected SdwnPacket(SdwnPacketType packetType, PacketType.Direction dir, ArrayList receivedBytes)
    {
        super(packetType, dir, receivedBytes);
    }
}
