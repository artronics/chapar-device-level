package artronics.chapar.packet;

import artronics.chapar.core.analyser.Analysable;
import artronics.chapar.core.logger.Log;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public abstract class AbstractPacket implements Packet
{

    protected AbstractPacket()
    {
    }

    @Override
    public List getAll()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List getHeader()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getHeaderSize()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDataSize()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List getData()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getType()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getSerialNumber()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException();
    }
}
