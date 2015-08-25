package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;
import java.util.Arrays;

public class FakePacketFactory
{
    public static final String FakeCsvHex = "DATA; IN; 0x7A; 0x7; 0x1; 0x2; 0x3; 0x2; 0x2; 0x1; 0x7E; ";
    public static final String FakeCsvForBeaconHex = "BEACON; IN; 0x7A; 0x7; 0x1; 0x2; 0x3; 0x2; 0x2; 0x2; 0x7E; ";
    public static final String FakeCsv = "DATA; IN; 122; 7; 1; 2; 3; 2; 2; 1; 126; ";
    public static final String FakeCsvForBeacon = "BEACON; IN; 122; 7; 1; 2; 3; 2; 2; 2; 126; ";
    private static final UnsignedByte startByte = UnsignedByte.of(Config.get().getByte("startByte"));
    private static final UnsignedByte stopByte = UnsignedByte.of(Config.get().getByte("stopByte"));
    private static ArrayList<UnsignedByte> goodPacket = new ArrayList<>();
    private static byte[] receivedBytes = new byte[9];

    public static ArrayList buildGoodPacket()
    {
        return buildGoodPacket(Packet.Type.DATA);
    }

    public static ArrayList buildGoodPacket(Packet.Type type)
    {
        byte[] b = buildGoodByteArray(type);
        goodPacket = UnsignedByte.toUnsignedByteArrayList(b, b.length);

        ArrayList<UnsignedByte> tmp = new ArrayList<>(goodPacket);
        goodPacket.clear();
        return tmp;
    }

    public static byte[] buildGoodByteArray(Packet.Type type)
    {
        receivedBytes[0] = startByte.byteValue();
        receivedBytes[1] = 7;
        receivedBytes[2] = 1;
        receivedBytes[3] = 2;
        receivedBytes[4] = 3;
        receivedBytes[5] = 2;
        receivedBytes[6] = 2;
        receivedBytes[7] = type.value.byteValue();
        receivedBytes[8] = stopByte.byteValue();

        byte[] tmp = Arrays.copyOf(receivedBytes, receivedBytes.length);

        return tmp;
    }
}
