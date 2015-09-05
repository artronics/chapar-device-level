package artronics.chapar.broker;

import artronics.chapar.core.configuration.Config;
import artronics.chapar.packet.Packet;

import java.util.List;

public class PacketToMessageConvertor
{
    private static final int START_BYTE = Config.get().getInt("START_BYTE");
    private static final int STOP_BYTE = Config.get().getInt("STOP_BYTE");

    public List convert(Packet packet)
    {
        List bytes = packet.getBytes();
        bytes.add(0, START_BYTE);
        bytes.add(STOP_BYTE);

        return bytes;
    }
}
