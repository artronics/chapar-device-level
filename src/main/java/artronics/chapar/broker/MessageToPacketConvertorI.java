package artronics.chapar.broker;

import artronics.chapar.core.configuration.Config;

import java.util.ArrayList;
import java.util.List;

public interface MessageToPacketConvertorI
{
    int MAX_Q_CAP = 1024;
    int START_BYTE = Config.get().getInt("START_BYTE");
    int STOP_BYTE = Config.get().getInt("STOP_BYTE");

    List<List<Integer>> generateRawPackets(List receivedData);
}
