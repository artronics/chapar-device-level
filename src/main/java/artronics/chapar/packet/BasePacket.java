package artronics.chapar.packet;

import java.util.List;

public class BasePacket implements Packet<String>
{
    private static long serialNumber = 0;
    protected final List bytes;
    private final String type;

    public BasePacket(List bytes)
    {
        this.bytes = bytes;
        this.type = "UNK";

        serialNumber++;
    }

    @Override
    public List getBytes()
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

}
