package artronics.chapar.packet;

import com.sun.istack.internal.NotNull;

import java.util.List;

public interface PacketFactory
{
    Packet create(@NotNull Packet.Type type,
                          @NotNull Packet.Direction direction,
                          @NotNull List<Integer> packetBytes);
}
