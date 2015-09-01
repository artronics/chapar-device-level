package artronics.chapar.protocol;

import artronics.chapar.core.configuration.Config;

import java.util.ArrayList;
import java.util.List;

public interface MessageBroker
{
    int START_BYTE = Config.get().getInt("START_BYTE");
    int STOP_BYTE = Config.get().getInt("STOP_BYTE");
    int LENGTH_INDEX = Config.get().getInt("PACKET_LENGTH_INDEX");

    List<List<Integer>> generateRawPackets(List receivedData);
}
