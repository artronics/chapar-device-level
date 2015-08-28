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
        return PacketProtocolHelper.validate(receivedBytes);
    }

    @Override
    public SdwnPacketType getType(ArrayList<UnsignedByte> receivedBytes)
    {
        return PacketProtocolHelper.getType(receivedBytes);
    }

    @Override
    public void clear()
    {
        byteArray.clear();
        isReady = false;
        isStarted = false;
        expetedSize = 0;
    }
    public interface PacketProtocolHelper
    {
        /**
         * Validate incoming bytes. Put all your validation rules here.
         * If Connection layer provides all packet bytes at
         * once, there is no need to invoke other methods in this interface.
         * For example in case of Serial Com all data is
         * available in InputStream at once.
         *
         * @param receivedBytes
         * @return
         */
        static boolean validate(ArrayList<UnsignedByte> receivedBytes)
        {
            //first lets get all bytes that we need for validation
            final UnsignedByte startByte = receivedBytes.get(0);//0 is always start byte
            final UnsignedByte length = getLength(receivedBytes);
            // +1 length value inside packet doesn't consider startByte
            final UnsignedByte stopByte = receivedBytes.get(length.intValue() + 1);

            //Now place your validation rules here.
            if (startByte.equals(START_BYTE) &&
                    // -2: length value inside packet
                    //doesn't consider startByte and stopByte
                    length.equals(UnsignedByte.of(receivedBytes.size() - 2)) &&
                    stopByte.equals(STOP_BYTE)) {
                return true;
            }else {
                return false;
            }
        }

        static UnsignedByte getLength(ArrayList<UnsignedByte> receivedBytes)
        {
            return receivedBytes.get(ByteMeaning.LENGTH.value);
        }

        static SdwnPacketType getType(ArrayList<UnsignedByte> receviedBytes)
        {
            UnsignedByte typeByte = receviedBytes.get(ByteMeaning.TYPE.value);
            for (SdwnPacketType type : SdwnPacketType.values()) {
                if (typeByte.equals(type.value))
                    return type;
            }

            return SdwnPacketType.MALFORMED;
        }
    }
}
