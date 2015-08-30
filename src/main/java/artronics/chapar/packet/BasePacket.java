package artronics.chapar.packet;

import artronics.chapar.core.analyser.Analysable;
import artronics.chapar.core.logger.Log;

import java.util.List;

public class BasePacket implements PacketContract, Analysable
{
    private static long packetSerialNumber = 0;
    protected final Packet.Type packetType;
    protected final Packet.Direction direction;
    private final List packetBytes;

    public BasePacket(Packet.Type packetType, Packet.Direction direction, List packetBytes)
    {
        this.packetType = packetType;
        this.direction = direction;
        this.packetBytes = packetBytes;

        writeCsv();
    }

    public static long getPacketSerialNumber()
    {
        return packetSerialNumber;
    }

    private static void incReceivedCounter()
    {
        packetSerialNumber++;
    }

    public Packet.Direction getDirection()
    {
        return direction;
    }

    public List<Integer> getPacketBytes()
    {
        return this.packetBytes;
    }

    public Packet.Type getType()
    {
        return packetType;
    }

    private void writeCsv()
    {
        String csv = toCsv();
        Log.packet().info(csv); //write to file
        Log.main().info(csv);// write to console
    }

    @Override
    public String toCsv()
    {
        return PacketToCsv.toCsv(this);
    }

}
