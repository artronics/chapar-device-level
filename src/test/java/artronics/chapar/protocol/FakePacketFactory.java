package artronics.chapar.protocol;

import artronics.chapar.packet.Packet;

import java.util.ArrayList;

public class FakePacketFactory
{
    public static final String FakeCsvHex = "DATA; IN; 0x7A; 0x7; 0x1; 0x2; 0x3; 0x2; 0x2; 0x1; 0x7E; ";
    public static final String FakeCsvForBeaconHex = "BEACON; IN; 0x7A; 0x7; 0x1; 0x2; 0x3; 0x2; 0x2; 0x2; 0x7E; ";
    public static final String FakeCsv = "DATA; IN; 122; 7; 1; 2; 3; 2; 2; 1; 126; ";
    public static final String FakeCsvForBeacon = "BEACON; IN; 122; 7; 1; 2; 3; 2; 2; 2; 126; ";
    protected static final int startByte = 122;
    protected static final int stopByte = 126;
    private static ArrayList<Integer> goodPacket = new ArrayList<>();
    private static int[] receivedInts = new int[9];

    public static ArrayList buildGoodPacket()
    {
        return buildGoodPacket(Packet.Type.DATA);
    }

    public static ArrayList buildGoodPacket(Packet.Type type)
    {
        goodPacket.add(startByte);
        goodPacket.add(7);
        goodPacket.add(1);
        goodPacket.add(2);
        goodPacket.add(3);
        goodPacket.add(2);
        goodPacket.add(2);
        goodPacket.add(type.getValue());
        goodPacket.add(stopByte);

        ArrayList<Integer> tmp = new ArrayList(goodPacket);
        goodPacket.clear();
        return tmp;
    }

}
