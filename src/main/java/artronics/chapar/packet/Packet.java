package artronics.chapar.packet;

import java.util.List;

public interface Packet
{
    List getBytes();

    long getSerialNumber();

    boolean isEmpty();
}
