package artronics.chapar.packet;

import java.util.List;

public interface Packet
{
    List getAll();

    List getHeader();

    int getHeaderSize();

    int getDataSize();

    List getData();

    String getType();

    long getSerialNumber();

    boolean isEmpty();
}
