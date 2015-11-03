package artronics.chapar.device;

import java.util.Set;

public interface DeviceConnection<C extends Connection>
{
    void establishConnection();

    Set<C> getConnections();

    void open(C connection);

    void close(C connection);

}
