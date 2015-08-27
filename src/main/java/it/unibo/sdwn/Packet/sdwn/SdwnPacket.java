package it.unibo.sdwn.Packet.sdwn;

import it.unibo.sdwn.Packet.AbstractBasePacket;
import it.unibo.sdwn.Packet.protocol.sdwn.SdwnPacketType;

import java.util.ArrayList;

public class SdwnPacket extends AbstractBasePacket<SdwnPacketType>
{

    /**
     * This id the minimum requirement for constructing a packet. We need Packet.Type and the Direction of packet and
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
