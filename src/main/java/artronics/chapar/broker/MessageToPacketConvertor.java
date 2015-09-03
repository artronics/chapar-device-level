package artronics.chapar.broker;

import artronics.chapar.packet.BasePacketFactory;
import artronics.chapar.packet.Packet;
import artronics.chapar.packet.PacketFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MessageToPacketConvertor implements MessageToPacketConvertorI
{
    private final ArrayDeque<Integer> dataQueue = new ArrayDeque<>(MAX_Q_CAP);
    private final ArrayList<ArrayList<Integer>> generatedRawPackets = new ArrayList<>();
    protected final List<Packet> generatedPackets = new ArrayList<>();
    private final ArrayList<Integer> thisPacket = new ArrayList<>();
    private int thisPacketExpectedSize = 0;
    private boolean isStarted = false;

    private PacketFactory packetFactory;

    @Override
    public List<Packet> generatePackets(List receivedData)
    {
        return createPackets(receivedData);
    }

    protected List<Packet> createPackets(List receivedData)
    {
        dataQueue.addAll(receivedData);
        while (!dataQueue.isEmpty()) {
            int thisData = dataQueue.removeFirst();

            if (thisData == START_BYTE
                    && !isStarted
                    && thisPacketExpectedSize == 0) {
                isStarted = Boolean.TRUE;

            }else if (isStarted) {
                if (thisPacketExpectedSize == 0) {
                    thisPacket.add(thisData);
                    thisPacketExpectedSize = thisData;

                }else if (thisPacket.size() < thisPacketExpectedSize) {
                    thisPacket.add(thisData);

                }else if (thisData == STOP_BYTE
                        && thisPacket.size() == thisPacketExpectedSize) {
                    ArrayList<Integer> newPacket = new ArrayList<>(thisPacket);
                    generatedRawPackets.add(newPacket);
                    thisPacket.clear();
                    isStarted = Boolean.FALSE;
                    thisPacketExpectedSize = 0;
                }
            }
        }
        for (List rawPacket : generatedRawPackets) {
            Packet packet = packetFactory.create(rawPacket);
            generatedPackets.add(packet);
        }
        generatedRawPackets.clear();

        List tmp = new ArrayList<>(generatedPackets);
        generatedPackets.clear();

        return tmp;
    }

    public void setPacketFactory(PacketFactory packetFactory)
    {
        this.packetFactory = packetFactory;
    }
}
