package artronics.chapar.packet;

import com.sun.istack.internal.NotNull;

import java.util.List;

public interface PacketFactory
{
    Packet create(@NotNull List<Integer> packetBytes);
}
