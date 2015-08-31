package unibo.it.protocol;

import artronics.chapar.core.logger.Log;
import artronics.chapar.packet.Packet;
import artronics.chapar.protocol.PacketProtocol;

import java.util.ArrayList;

final class SdwnPacketProtocol implements PacketProtocol
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
    public Packet.Direction getDirection(ArrayList receivedBytes)
    {
        return SdwnProtocolHelper.chooseDirection(receivedBytes);
    }

    @Override
    public Packet.Type getType(ArrayList receivedBytes)
    {
        return SdwnProtocolHelper.getType(receivedBytes);
    }

    @Override
    public void clear()
    {
        byteArray.clear();
        isReady = false;
        isStarted = false;
        expetedSize = 0;
    }

    public static int getStartByte()
    {
        return START_BYTE;
    }

    public static int getStopByte()
    {
        return STOP_BYTE;
    }
}
