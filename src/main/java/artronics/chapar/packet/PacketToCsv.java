package artronics.chapar.packet;

import java.util.List;

final class PacketToCsv
{
    //European countries use ";" as
    //CSV separator because "," is their digit separator
    public static final String CSV_SEPARATOR = ";";
    private static String csv;


    public static String toCsv(BasePacket packet)
    {
        csv = "";

        csv += String.format("%-15s", packet.getType());
        csvAppend();

        csv += String.format("%-5d", BasePacket.getPacketSerialNumber());
        csvAppend();

        csv += String.format("%-3s ", packet.getDirection());
        csvAppend();

        addPacketBytes(packet.getPacketBytes());

        //Put an extra separator at the end
        csvAppend();

        return csv;
    }

    private static void addPacketBytes(List<Integer> receivedBytes)
    {
        for (int ub : receivedBytes) {
            csv += String.format("%-3d ", ub);
            csvAppend();
        }
    }

    private static void csvAppend()
    {
        csv += CSV_SEPARATOR;
    }
}
