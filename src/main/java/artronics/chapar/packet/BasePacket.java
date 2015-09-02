package artronics.chapar.packet;

import java.util.ArrayList;
import java.util.List;

public class BasePacket extends AbstractPacket
{
    private static long serialNumber = 0;
    protected final List header;
    protected final List data;
    protected String type;

    public BasePacket(List header, List data)
    {
        this.header = header;
        this.data = data;

        serialNumber++;
    }

    public BasePacket(List header, List data, String type)
    {
        this.header = header;
        this.data = data;
        this.type = type;
    }

    @Override
    public List getAll()
    {
        List all = new ArrayList<>(header);
        all.addAll(data);
        return all;
    }

    @Override
    public List getHeader()
    {
        return header;
    }

    @Override
    public int getHeaderSize()
    {
        return header.size();
    }

    @Override
    public int getDataSize()
    {
        return data.size();
    }

    @Override
    public List getData()
    {
        return data;
    }

    @Override
    public String getType()
    {
        return type != null ? type : "UNK";
    }

    @Override
    public long getSerialNumber()
    {
        return serialNumber;
    }

    @Override
    public boolean isEmpty()
    {
        return header.isEmpty() && data.isEmpty();
    }
}
