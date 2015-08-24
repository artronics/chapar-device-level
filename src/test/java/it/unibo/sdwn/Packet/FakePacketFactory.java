package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

public class FakePacketFactory
{
    private static final UnsignedByte startByte = UnsignedByte.of(Config.get().getByte("startByte"));
    private static final UnsignedByte stopByte = UnsignedByte.of(Config.get().getByte("stopByte"));
    static ArrayList<UnsignedByte> goodPacket = new ArrayList<>();

    static ArrayList buildGoodPacket()
    {
        goodPacket.add(UnsignedByte.of(startByte));
        goodPacket.add(UnsignedByte.of(7)); //packet length
        goodPacket.add(UnsignedByte.of(1));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(3));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(1));//type=data
        goodPacket.add(UnsignedByte.of(stopByte));

        return goodPacket;
    }
}
