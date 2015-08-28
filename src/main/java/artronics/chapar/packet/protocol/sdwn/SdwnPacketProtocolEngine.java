package artronics.chapar.packet.protocol.sdwn;

import artronics.chapar.app.logger.Log;
import artronics.chapar.helper.UnsignedByte;

import java.util.ArrayList;

//what if we get START_BYTE and system gets corrupted value for
//STOP_BYTE. system keeps adding bytes to byteArray until next STOP_BYTE.
//see LUNGHEZZA_FRAME_MAX in original code in Sdwn_Protocol.java
//TODO [Potential Bug] There must be some max value for packet length

/**
 * Use this class when you get bytes from source and you want to construct a packet. Use addByte method to add received
 * byte. If you want to change SDWN protocol for the receiving packets this is where you should add new protocol
 * engine.
 */
public final class SdwnPacketProtocolEngine implements SdwnPacketProtocol
{
    private ArrayList<UnsignedByte> byteArray = new ArrayList(0);
    private boolean isReady = false;
    private boolean isStarted = false;
    private int expetedSize = 0;

    public SdwnPacketProtocolEngine()
    {
    }

    @Override
    public synchronized void addByte(UnsignedByte receivedByte)
    {
        final int size = byteArray.size();
        if (size == 0 && receivedByte.equals(START_BYTE)) {
            //Do nothing just clear this clear and get ready for new packet
            clear();
            isStarted = true;
        }else if (isStarted) {
            if (expetedSize == 0) {
                byteArray.add(receivedByte);
                expetedSize = receivedByte.intValue();
            }else if (size < expetedSize) {
                byteArray.add(receivedByte);
            }else if (size == expetedSize && receivedByte.equals(STOP_BYTE)) {
                //Since in protocol definition we difine STOP and START
                //bytes, we add to them to beginning and end of packet to
                //match every thing as is defined in protocol.
                byteArray.add(0,START_BYTE);
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
    public ArrayList<UnsignedByte> getReceivedBytes()
    {
        return byteArray;
    }

    //TODO reimplement this method
    @Override
    public boolean isValid(ArrayList<UnsignedByte> receivedBytes)
    {
        return SdwnPacketProtocolHelper.validate(receivedBytes);
    }

    @Override
    public SdwnPacketType getType(ArrayList<UnsignedByte> receivedBytes)
    {
        return SdwnPacketProtocolHelper.getType(receivedBytes);
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
