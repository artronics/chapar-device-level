package unibo.it.protocol;

import artronics.chapar.core.configuration.Config;
import artronics.chapar.packet.Packet;

import java.util.ArrayList;

final class SdwnProtocolHelper
{
    private static final int SINK_ADDRESS = Config.get().getInt("SINK_ADDRESS");

    static boolean validate(ArrayList<Integer> receivedBytes)
    {
        //first lets get all bytes that we need for validation
        final int startByte = receivedBytes.get(0);//0 is always start byte
        final int length = getLength(receivedBytes);
        // +1 length value inside packet doesn't consider startByte
        final int stopByte = receivedBytes.get(length + 1);

        //Now place your validation rules here.
        return startByte == SdwnPacketProtocol.getStartByte() &&
                (length == receivedBytes.size() - 2) &&// -startByte - stopByte
                (stopByte == SdwnPacketProtocol.getStopByte());

    }

    static int getLength(ArrayList<Integer> receivedBytes)
    {
        return receivedBytes.get(ByteMeaning.LENGTH.getValue());
    }

    static Packet.Type getType(ArrayList<Integer> receviedBytes)
    {
        int typeIndex = receviedBytes.get(ByteMeaning.TYPE.getValue());
        for (Packet.Type type : Packet.Type.values()) {
            if (typeIndex == type.getValue())
                return type;
        }

        return Packet.Type.MALFORMED;
    }

    //it gets two part of address and returns int address
    static int getIntAddress(int sourceL, int sourceH)
    {
        int addr = sourceH;
        addr = (addr << 8) | sourceL;

        return addr;
    }

    static Packet.Direction chooseDirection(ArrayList<Integer> receivedBytes)
    {
        int sourceL = ByteMeaning.SOURCE_L.getValue();
        int sourceH = ByteMeaning.SOURCE_H.getValue();

        int sourceAddL = receivedBytes.get(sourceL);
        int sourceAddH = receivedBytes.get(sourceH);
        int source_addr = getIntAddress(sourceAddL, sourceAddH);
        //If packet is beacon and the address matches with sink it has OUT direction.
        if (source_addr == SINK_ADDRESS) {
            return Packet.Direction.OUT;
        }

        return Packet.Direction.IN;
    }
}
