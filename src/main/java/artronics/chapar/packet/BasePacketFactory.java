package artronics.chapar.packet;

import artronics.chapar.core.analyser.ToCsv;

import java.util.List;

final class BasePacketFactory implements PacketFactory
{
    @Override
    public Packet create(List<Integer> packetBytes)
    {
        System.out.println(ToCsv.creat(packetBytes));

        return new BasePacket(packetBytes);
    }

}
