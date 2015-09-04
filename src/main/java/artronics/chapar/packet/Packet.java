package artronics.chapar.packet;

import java.util.List;

public interface Packet<T>
{
    List getBytes();

    long getSerialNumber();

    T getType();

    boolean isEmpty();
}
