package artronics.chapar.packet;

import artronics.chapar.core.analyser.Analysable;
import artronics.chapar.core.analyser.ToCsv;

import java.util.List;

public class BasePacket implements Packet<String>
{
    private final Analysable csv = new CsvWriter();

    private static long serialNumber = 0;
    protected final List bytes;
    private final String type;

    public BasePacket(List bytes)
    {
        this.bytes = bytes;
        this.type = "UNK";

        serialNumber++;

        ToCsv.write("Packets", csv);
    }

    @Override
    public List getPacketContent()
    {
        return bytes;
    }

    @Override
    public long getSerialNumber()
    {
        return serialNumber;
    }

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public boolean isEmpty()
    {
        return bytes.isEmpty();
    }

    private final class CsvWriter implements Analysable
    {
        @Override
        public String toCsv()
        {
            return ToCsv.create(getSerialNumber(),
                                getType(),
                                getPacketContent()
            );
        }
    }
}
