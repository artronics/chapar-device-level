package artronics.chapar.packet.sdwn;

import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.packet.protocol.PacketProtocol;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketType;

import java.util.ArrayList;

import static artronics.chapar.packet.PacketType.Direction;
import static artronics.chapar.packet.protocol.sdwn.SdwnPacketProtocolHelper.chooseDirection;
import static artronics.chapar.packet.protocol.sdwn.SdwnPacketProtocolHelper.validate;
import static artronics.chapar.packet.protocol.sdwn.SdwnPacketType.MALFORMED;

public class SdwnPacketFactory implements PacketFactory<SdwnPacket, SdwnPacketType>
{
    private final PacketProtocol<SdwnPacketType> packetProtocol;

    public SdwnPacketFactory(PacketProtocol packetProtocol)
    {
        this.packetProtocol = packetProtocol;
    }

    @Override
    public SdwnPacket createPacket(ArrayList<UnsignedByte> receivedBytes)
    {
        SdwnPacket packet;
        if (validate(receivedBytes)) {
            SdwnPacketType type = packetProtocol.getType(receivedBytes);
            Direction direction = chooseDirection(receivedBytes);
            packet = new SdwnPacket(type, direction, receivedBytes);

            return packet;
        }
        packet = new SdwnPacket(MALFORMED, Direction.IN, receivedBytes);

        return packet;
    }

    @Override
    public SdwnPacket createPacket(SdwnPacketType type, Direction direction, ArrayList bytes)
    {
        return new SdwnPacket(type, direction, bytes);
    }
}
