package artronics.chapar;

import artronics.chapar.core.configuration.Config;
import artronics.chapar.packet.Packet;

import java.util.ArrayList;

public class FakePacketFactory
{
    public static final int START_BYTE = Config.get().getInt("START_BYTE");
    public static final int STOP_BYTE = Config.get().getInt("STOP_BYTE");
    public static final int LENGTH_INDEX = Config.get().getInt("PACKET_LENGTH_INDEX");

    public static final String FakeCsvHex = "DATA; IN; 0x7A; 0x7; 0x1; 0x2; 0x3; 0x2; 0x2; 0x1; 0x7E; ";
    public static final String FakeCsvForBeaconHex = "BEACON; IN; 0x7A; 0x7; 0x1; 0x2; 0x3; 0x2; 0x2; 0x2; 0x7E; ";
    public static final String FakeCsv = "DATA; IN; 122; 7; 1; 2; 3; 2; 2; 1; 126; ";
    public static final String FakeCsvForBeacon = "BEACON; IN; 122; 7; 1; 2; 3; 2; 2; 2; 126; ";
    private static ArrayList<Integer> goodPacket = new ArrayList();
    private static int[] receivedInts = new int[9];

    public static ArrayList buildGoodPacket()
    {
        return buildGoodPacket(Packet.Type.DATA);
    }

    public static ArrayList buildGoodPacket(Packet.Type type)
    {
        goodPacket.add(START_BYTE);
        goodPacket.add(7);
        goodPacket.add(1);
        goodPacket.add(2);
        goodPacket.add(3);
        goodPacket.add(2);
        goodPacket.add(2);
        goodPacket.add(type.getValue());
        goodPacket.add(STOP_BYTE);

        ArrayList<Integer> tmp = new ArrayList(goodPacket);
        goodPacket.clear();
        return tmp;
    }
}
