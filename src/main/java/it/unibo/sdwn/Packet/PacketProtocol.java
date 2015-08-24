package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;

public interface PacketProtocol extends Packet
{
    /**
     * Validate incoming bytes. Put all your validation rules here. If Connection layer provides all packet bytes at
     * once, there is no need to invoke other methods in this interface. For example in case of Serial Com all data is
     * available in InputStream at once.
     *
     * @param receivedBytes
     * @return
     */
    static boolean validate(ArrayList<UnsignedByte> receivedBytes)
    {
        boolean isValid;
        //first lets get all bytes that we need for validation
        final UnsignedByte startByte = receivedBytes.get(0);//0 is always start byte
        final UnsignedByte length = receivedBytes.get(ByteMeaning.LENGTH.value);
        // +1 length value inside packet doesn't consider startByte
        final UnsignedByte stopByte = receivedBytes.get(length.intValue() + 1);

        //Now place your validation rules here.
        if (startByte.equals(START_BYTE) &&
                // -2: length value inside packet
                //doesn't consider startByte and stopByte
                length.equals(UnsignedByte.of(receivedBytes.size() - 2)) &&
                stopByte.equals(STOP_BYTE)) {
            isValid = true;
        }else {
            isValid = false;
        }

        return isValid;
    }

    void addByte(UnsignedByte receivedByte) throws MalformedPacketException;

    boolean isReady();

    ArrayList<UnsignedByte> getPacket() throws MalformedPacketException;

    void validateReceivedBytes(ArrayList<UnsignedByte> receivedBytes) throws MalformedPacketException;
}
