package artronics.chapar.packet.protocol.sdwn;

import artronics.chapar.controller.sdwn.SdwnController;
import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.PacketType;

import java.util.ArrayList;

public interface SdwnPacketProtocolHelper
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
        //first lets get all bytes that we need for validation
        final UnsignedByte startByte = receivedBytes.get(0);//0 is always start byte
        final UnsignedByte length = getLength(receivedBytes);
        // +1 length value inside packet doesn't consider startByte
        final UnsignedByte stopByte = receivedBytes.get(length.intValue() + 1);

        //Now place your validation rules here.
        if (startByte.equals(SdwnPacketProtocol.START_BYTE) &&
                // -2: length value inside packet
                //doesn't consider startByte and stopByte
                length.equals(UnsignedByte.of(receivedBytes.size() - 2)) &&
                stopByte.equals(SdwnPacketProtocol.STOP_BYTE)) {
            return true;
        }else {
            return false;
        }
    }

    static UnsignedByte getLength(ArrayList<UnsignedByte> receivedBytes)
    {
        return receivedBytes.get(SdwnPacketProtocol.ByteMeaning.LENGTH.value);
    }

    static SdwnPacketType getType(ArrayList<UnsignedByte> receviedBytes)
    {
        UnsignedByte typeByte = receviedBytes.get(SdwnPacketProtocol.ByteMeaning.TYPE.value);
        for (SdwnPacketType type : SdwnPacketType.values()) {
            if (typeByte.equals(type.value))
                return type;
        }

        return SdwnPacketType.MALFORMED;
    }

    static int getIntAddress(UnsignedByte sourceL, UnsignedByte sourceH)
    {
        int addr = sourceH.intValue();
        addr = (addr << 8) | (sourceL.intValue());

        return addr;
    }

    static PacketType.Direction chooseDirection(ArrayList<UnsignedByte> receivedBytes)
    {
        int sourceLIndex = SdwnPacketProtocol.ByteMeaning.SOURCE_L.getValue();
        int sourceHIndex = SdwnPacketProtocol.ByteMeaning.SOURCE_H.getValue();
        UnsignedByte sourceL = UnsignedByte.of(receivedBytes.get(sourceLIndex));
        UnsignedByte sourceH = UnsignedByte.of(receivedBytes.get(sourceHIndex));
        int source_addr = getIntAddress(sourceL, sourceH);
        //If packet is beacon and the address matches with sink it has OUT direction.
        if (source_addr == SdwnController.SINK_ADDRESS) {
            return PacketType.Direction.OUT;
        }
        return PacketType.Direction.IN;
    }
}
