package artronics.chapar.packet.sdwn;

import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.packet.protocol.PacketProtocol;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketProtocol;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketProtocolHelper;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketType;

import java.util.ArrayList;

import static artronics.chapar.packet.PacketType.Direction;
import static artronics.chapar.packet.protocol.sdwn.SdwnPacketProtocol.START_BYTE;
import static artronics.chapar.packet.protocol.sdwn.SdwnPacketProtocol.STOP_BYTE;
import static artronics.chapar.packet.protocol.sdwn.SdwnPacketProtocolHelper.chooseDirection;
import static artronics.chapar.packet.protocol.sdwn.SdwnPacketProtocolHelper.validate;
import static artronics.chapar.packet.protocol.sdwn.SdwnPacketType.MALFORMED;

public class SdwnPacketFactory implements PacketFactory<SdwnPacket, SdwnPacketType>
{
    @Override
    public SdwnPacket createPacket(ArrayList<UnsignedByte> receivedBytes)
    {
        SdwnPacket packet;
        if (validate(receivedBytes)) {
            SdwnPacketType type = SdwnPacketProtocolHelper.getType(receivedBytes);
            Direction direction = chooseDirection(receivedBytes);
            packet = new SdwnPacket(type, direction, receivedBytes);

            return packet;
        }
        packet = new SdwnPacket(MALFORMED, Direction.IN, receivedBytes);

        return packet;
    }

    @Override
    public SdwnPacket createPacket(SdwnPacketType type, Direction direction, ArrayList<UnsignedByte> bytes)
    {
        return new SdwnPacket(type, direction, bytes);
    }
    public static ArrayList<UnsignedByte> dummyData(){
        ArrayList<UnsignedByte> bytes = new ArrayList<>();
        bytes.add(START_BYTE);
        bytes.addAll(addDataHeaderFromSink());
        bytes.add(UnsignedByte.of(70));
        bytes.add(UnsignedByte.of(80));
        bytes.add(UnsignedByte.of(90));
        bytes.add(STOP_BYTE);

        return bytes;
    }
    private static ArrayList<UnsignedByte> addDataHeaderFromSink(){
        ArrayList<UnsignedByte> header = new ArrayList<>();
        header.add(UnsignedByte.of(10+3));//send 3 bytes
        header.add(UnsignedByte.of(1));//NET_ID
        header.add(UnsignedByte.of(0));//source addr
        header.add(UnsignedByte.of(0));

        header.add(UnsignedByte.of(0));
        header.add(UnsignedByte.of(30));//to 30

        header.add(UnsignedByte.of(0));//type data ->0
        header.add(UnsignedByte.of(20));//ttl max

        header.add(UnsignedByte.of(0));
        header.add(UnsignedByte.of(0));

        return header;
    }

}
