package artronics.chapar.packet;

import java.util.List;

final class BasePacketFactory implements PacketFactory
{
    @Override
    public BasePacket create(List<Integer> packetBytes)
    {
        return new BasePacket(packetBytes);
    }

}
