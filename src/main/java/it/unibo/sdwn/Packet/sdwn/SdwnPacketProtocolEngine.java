package it.unibo.sdwn.Packet.sdwn;

import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

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
public final class SdwnPacketProtocolEngine implements PacketProtocolHelper
{
    private ArrayList<UnsignedByte> byteArray = new ArrayList(0);
    private boolean isReady = false;
    private boolean isStarted = false;
    private int expetedSize = 0;

    public SdwnPacketProtocolEngine()
    {
    }

    @Override
    public synchronized void addByte(UnsignedByte receivedByte) throws MalformedPacketException
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
                isReady = true;
                isStarted = false;
            }else {
                clear();
                Log.main().debug("Malformed packet received");
                throw new MalformedPacketException("Malformed packet received");
            }
        }
    }

    @Override
    public synchronized boolean isReady()
    {
        return isReady;
    }

    @Override
    public ArrayList<UnsignedByte> getPacket() throws MalformedPacketException
    {
        if (isReady()) {
            //Clear this object before passing the byteArray
            ArrayList temp = new ArrayList(byteArray);
            clear();
            return temp;
        }else {
            clear();
            Log.main().debug("Attempt to get not ready packet");
            throw new MalformedPacketException("Attempt to get not ready packet");
        }
    }

    @Override
    public void validateReceivedBytes(ArrayList<UnsignedByte> receivedBytes) throws MalformedPacketException
    {
        for (UnsignedByte b : receivedBytes) {
            addByte(b);
        }
        clear();
    }

    private synchronized void clear()
    {
        byteArray.clear();
        isReady = false;
        isStarted = false;
        expetedSize = 0;
    }
}
