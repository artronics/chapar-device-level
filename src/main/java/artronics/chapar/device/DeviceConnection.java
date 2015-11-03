package artronics.chapar.device;

import java.util.Hashtable;
import java.util.Set;

public interface DeviceConnection<C extends Connection>
{
    void establishConnection();

    Hashtable<String,C> getConnections();

    void open(C connection);

    void close(C connection);

}
