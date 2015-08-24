package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

public class FakePacketFactory
{
    public static final String FakeCsv = "DATA; IN; 0x7A; 0x7; 0x1; 0x2; 0x3; 0x2; 0x2; 0x1; 0x7E; ";
    public static final String FakeCsvForBeacon = "BEACON; IN; 0x7A; 0x7; 0x1; 0x2; 0x3; 0x2; 0x2; 0x2; 0x7E; ";
    private static final UnsignedByte startByte = UnsignedByte.of(Config.get().getByte("startByte"));
    private static final UnsignedByte stopByte = UnsignedByte.of(Config.get().getByte("stopByte"));
    private static ArrayList<UnsignedByte> goodPacket = new ArrayList<>();

    protected static ArrayList buildGoodPacket()
    {
        return buildGoodPacket(Packet.Type.DATA);
    }

    protected static ArrayList buildGoodPacket(Packet.Type type)
    {
        goodPacket.add(UnsignedByte.of(startByte));
        goodPacket.add(UnsignedByte.of(7)); //packet length
        goodPacket.add(UnsignedByte.of(1));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(3));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(type.value));//type=data
        goodPacket.add(UnsignedByte.of(stopByte));

        ArrayList<UnsignedByte> tmp = new ArrayList<>(goodPacket);
        goodPacket.clear();
        return tmp;
    }
}
