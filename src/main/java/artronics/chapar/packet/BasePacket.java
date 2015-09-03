package artronics.chapar.packet;

import java.util.ArrayList;
import java.util.List;

public class BasePacket extends AbstractPacket
{
    private static long serialNumber = 0;
    protected List header;
    protected List data;
    protected List all;
    protected String type;

    public BasePacket(List all)
    {
        this.all = all;

        serialNumber++;
    }

    public BasePacket(List header, List data)
    {
        this.header = header;
        this.data = data;
        this.all = joinDataAndHeader(header, data);

        serialNumber++;
    }

    public BasePacket(List header, List data, String type)
    {
        this.header = header;
        this.data = data;
        this.type = type;
        this.all = joinDataAndHeader(header, data);

        serialNumber++;
    }

    private List joinDataAndHeader(List header, List data)
    {
        List all = new ArrayList<>(header);
        all.addAll(data);
        return all;
    }

    @Override
    public List getAll()
    {
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
