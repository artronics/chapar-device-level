package artronics.chapar.packet;

import java.util.List;

public interface Packet<T>
{
    List getPacketContent();

    long getSerialNumber();

    T getType();

    boolean isEmpty();
}
