package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;
import it.unibo.sdwn.trasport.exceptions.PacketNotReadyException;

import java.util.ArrayList;

//what if we get START_BYTE and system gets corrupted value for
//STOP_BYTE. system keeps adding bytes to byteArray until next STOP_BYTE.
//see LUNGHEZZA_FRAME_MAX in original code in Sdwn_Protocol.java
//TODO [Potential Bug] There must be some max value for packet length
public class PacketProtocol
{
    private final static UnsignedByte START_BYTE =UnsignedByte.of(Config.get().getByte("startByte"));
    private final static UnsignedByte STOP_BYTE =UnsignedByte.of(Config.get().getByte("stopByte"));
    private ArrayList<UnsignedByte> byteArray = new ArrayList(0);
    private boolean isReady = false;
    private boolean isStarted = false;
    private int expetedSize = 0;

    public synchronized ArrayList<UnsignedByte> getPacket() throws PacketNotReadyException
    {
        if (isReady()) {
            //Clear this object before passing the byteArray
            ArrayList temp = new ArrayList(byteArray);
            clear();
            return temp;
        }else {
            clear();
            Log.main().debug("Attempt to get not ready packet");
            throw new PacketNotReadyException();
        }
    }

    public synchronized boolean isReady()
    {
        return isReady;
    }

    private synchronized void clear()
    {
        byteArray.clear();
        isReady = false;
        isStarted = false;
        expetedSize = 0;
    }

    public synchronized void addByte(UnsignedByte receivedByte) throws MalformedPacketException
    {
        int size = byteArray.size();
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
                throw new MalformedPacketException();
            }
        }
    }
}
