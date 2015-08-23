package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;
import it.unibo.sdwn.trasport.exceptions.PacketNotReadyException;

import java.util.ArrayList;

//what if we get startByte and system gets corrupted value for
//stopByte. system keeps adding bytes to byteArray until next stopByte.
//see LUNGHEZZA_FRAME_MAX in original code in Sdwn_Protocol.java
//TODO [Potential Bug] There must be some max value for packet length
public class PacketProtocol
{
    private final byte startByte = Config.get().getByte("startByte");
    private final byte stopByte = Config.get().getByte("stopByte");
    private ArrayList byteArray = new ArrayList<Byte>(0);
    private boolean isReady = false;
    private boolean isStarted = false;
    private int expetedSize = 0;

    public synchronized ArrayList<Byte> getPacket() throws PacketNotReadyException
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

    public synchronized void add(byte receivedByte) throws MalformedPacketException
    {
        int size = byteArray.size();
        if (size == 0 && receivedByte == startByte) {
            //Do nothing just clear this clear and get ready for new packet
            clear();
            isStarted = true;
        }else if (isStarted) {
            if (expetedSize == 0) {
                byteArray.add(receivedByte);
                expetedSize = (int) receivedByte;
            }else if (size < expetedSize) {
                byteArray.add(receivedByte);
            }else if (size == expetedSize && receivedByte == stopByte) {
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
