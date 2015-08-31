package artronics.chapar.packet;

import java.util.List;

final class BasePacketFactory implements PacketFactory
{
    @Override
    public Packet create(Packet.Type type, Packet.Direction direction, List<Integer> packetBytes)
    {
        return new BasePacket(type, direction, packetBytes);
    }

}
