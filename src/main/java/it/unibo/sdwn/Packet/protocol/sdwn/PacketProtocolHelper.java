package it.unibo.sdwn.Packet.protocol.sdwn;

import it.unibo.sdwn.Packet.protocol.sdwn.SdwnPacketProtocol;
import it.unibo.sdwn.Packet.protocol.sdwn.SdwnPacketType;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;

import java.util.ArrayList;

public interface PacketProtocolHelper extends SdwnPacketProtocol
{
    /**
     * Validate incoming bytes. Put all your validation rules here. If Connection layer provides all packet bytes at
     * once, there is no need to invoke other methods in this interface. For example in case of Serial Com all data is
     * available in InputStream at once.
     *
     * @param receivedBytes
     * @return
     */
    static void validate(ArrayList<UnsignedByte> receivedBytes) throws MalformedPacketException
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
        }else {
            throw new MalformedPacketException("SdwnPacket validation failed");
        }
    }

    static UnsignedByte getLength(ArrayList<UnsignedByte> receivedBytes)
    {
        return receivedBytes.get(ByteMeaning.LENGTH.value);
    }

    static SdwnPacketType getType(ArrayList<UnsignedByte> receviedBytes) throws MalformedPacketException
    {
        UnsignedByte typeByte = receviedBytes.get(ByteMeaning.TYPE.value);
        for (SdwnPacketType type : SdwnPacketType.values()) {
            if (typeByte.equals(type.value))
                return type;
        }
        //if type is not among the values we defined, it means packet is malformed.
        throw new MalformedPacketException("Type is not correct");
    }
}
