package artronics.chapar.protocol;

import artronics.chapar.core.configuration.Config;
import artronics.chapar.core.logger.Log;
import artronics.chapar.packet.Packet;

import java.util.ArrayList;

public class SdwnPacketProtocol implements PacketProtocol
{
    private static final int START_BYTE = 122;
    private static final int STOP_BYTE = 126;

    private ArrayList<Integer> byteArray = new ArrayList(0);
    private boolean isReady = false;
    private boolean isStarted = false;
    private int expetedSize = 0;

    @Override
    public synchronized void addByte(int receivedByte)
    {
        final int size = byteArray.size();
        if (size == 0 && receivedByte == START_BYTE) {
            //Do nothing just clear this clear and get ready for new packet
            clear();
            isStarted = true;
        }else if (isStarted) {
            if (expetedSize == 0) {
                byteArray.add(receivedByte);
                expetedSize = receivedByte;
            }else if (size < expetedSize) {
                byteArray.add(receivedByte);
            }else if (size == expetedSize && receivedByte == STOP_BYTE) {
                //Since in protocol definition we difine STOP and START
                //bytes, we add to them to beginning and end of packet to
                //match every thing as is defined in protocol.
                byteArray.add(0, START_BYTE);
                byteArray.add(STOP_BYTE);
                isReady = true;
                isStarted = false;
            }else {
                clear();
                Log.main().debug("Malformed packet received");
            }
        }
    }

    @Override
    public synchronized boolean isPacketReady()
    {
        return isReady;
    }

    @Override
    public ArrayList<Integer> getReceivedBytes()
    {
        return byteArray;
    }

    @Override
    public boolean isValid(ArrayList<Integer> receivedBytes)
    {
        return false;
    }

    @Override
    public Packet.Type getType(ArrayList receivedBytes)
    {
        return Packet.Type.REPORT;
    }

    @Override
    public void clear()
    {
        byteArray.clear();
        isReady = false;
        isStarted = false;
        expetedSize = 0;
    }

}
