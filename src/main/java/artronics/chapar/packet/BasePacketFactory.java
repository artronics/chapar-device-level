package artronics.chapar.packet;

import artronics.chapar.core.analyser.ToCsv;

import java.util.List;

public final class BasePacketFactory implements PacketFactory
{
    @Override
    public Packet create(List<Integer> packetBytes)
    {
        System.out.println(ToCsv.creat(packetBytes));
        return new BasePacket(packetBytes);
    }
//    public static Packet create(List<Integer> packetBytes)
//    {
//        ToCsv.creat(packetBytes);
//        return new BasePacket(packetBytes);
//    }

}
