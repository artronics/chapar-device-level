package artronics.chapar.packet;

import java.util.List;

final class BasePacketFactory implements PacketFactory
{
    @Override
    public Packet create(List<Integer> packetBytes)
    {
        return AbstractPacket.create(packetBytes);
    }

}
