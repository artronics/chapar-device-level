package it.unibo.sdwn.packet.sdwn;

import it.unibo.sdwn.packet.AbstractBasePacket;
import it.unibo.sdwn.packet.protocol.sdwn.SdwnPacketType;

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
    protected SdwnPacket(SdwnPacketType packetType, Direction dir, ArrayList receivedBytes)
    {
        super(packetType, dir, receivedBytes);
    }
}
