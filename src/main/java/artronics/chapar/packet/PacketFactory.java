package artronics.chapar.packet;

import java.util.List;

public interface PacketFactory
{
    PacketContract create(Packet.Type type, Packet.Direction direction, List<Integer> packetBytes);
}
