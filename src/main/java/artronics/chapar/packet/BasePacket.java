package artronics.chapar.packet;

import java.util.List;

public class BasePacket implements Packet
{
    private static long serialNumber = 0;
    protected List bytes;

    public BasePacket(List bytes)
    {
        this.bytes = bytes;

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
    public boolean isEmpty()
    {
        return bytes.isEmpty();
    }

}
