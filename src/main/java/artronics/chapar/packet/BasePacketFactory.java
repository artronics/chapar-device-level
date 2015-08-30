package artronics.chapar.packet;

import java.util.List;

public class BasePacketFactory implements PacketFactory
{
    @Override
    public PacketContract create(Packet.Type type, Packet.Direction direction, List<Integer> packetBytes)
    {
        return new BasePacket(type, direction, packetBytes);
    }
}
