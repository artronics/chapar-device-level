package artronics.chapar.protocol;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MessageBrokerImpl implements MessageBroker
{
    private static final int MAX_Q_CAP = 1024;

    private final ArrayDeque<Integer> dataQueue = new ArrayDeque<>(MAX_Q_CAP);
    private final ArrayList<ArrayList<Integer>> createdPacketes = new ArrayList<>();
    private final ArrayList<Integer> thisPacket = new ArrayList<>();
    private int thisPacketExpectedSize = 0;
    private boolean isStarted = false;

    @Override
    public synchronized List<List<Integer>> generateRawPackets(List receivedData)
    {
        fillQueue(receivedData);

        return createPackets();
    }

    private void fillQueue(List<Integer> receivedData)
    {
        dataQueue.addAll(receivedData);
    }

    private List<List<Integer>> createPackets()
    {
        while (!dataQueue.isEmpty()) {
            int thisData = dataQueue.removeFirst();

            if (thisData == START_BYTE
                    && !isStarted
                    && thisPacketExpectedSize == 0) {
                isStarted = Boolean.TRUE;
                thisPacket.add(thisData);

            }else if (isStarted) {
                if (thisPacketExpectedSize == 0) {
                    thisPacket.add(thisData);
                    thisPacketExpectedSize = thisData;

                }else if (thisPacket.size() <= thisPacketExpectedSize) {
                    thisPacket.add(thisData);

                }else if (thisData == STOP_BYTE
                        && thisPacket.size() == thisPacketExpectedSize + 1) {
                    thisPacket.add(thisData);
                    ArrayList<Integer> newPacket = new ArrayList<>(thisPacket);
                    createdPacketes.add(newPacket);
                    thisPacket.clear();
                    isStarted = Boolean.FALSE;
                    thisPacketExpectedSize = 0;
                }
            }
        }
        List<List<Integer>> tmp = new ArrayList<>(createdPacketes);
        createdPacketes.clear();

        return tmp;
    }
}
