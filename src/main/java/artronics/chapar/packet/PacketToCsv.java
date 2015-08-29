package artronics.chapar.packet;

import artronics.chapar.helper.UnsignedByte;

import java.util.ArrayList;

class PacketToCsv
{
    //TODO change this class to non static.
    //TODO each line must end with two csv deparators
    //European countries use ";" as
    //CSV separator because "," is their digit separator
    public static final String CSV_SEPARATOR = ";";
    private static String csv;


    public static String toCsv(AbstractBasePacket packet)
    {
        csv = "";

        csv += String.format("%-15s", packet.getType());
        csvAppend();

        csv += String.format("%-5d", packet.getPacketSerialNumber());
        csvAppend();

        csv += String.format("%-3s ", packet.getDirection());
        csvAppend();

        addPacketBytes(packet.getPacketBytes());

        //Put an extra separator at the end
        csvAppend();

        return csv;
    }

    private static void addPacketBytes(ArrayList<UnsignedByte> receivedBytes)
    {
        for (UnsignedByte ub : receivedBytes) {
            csv += String.format("%-3d ", ub.intValue());
            csvAppend();
        }
    }

    private static void csvAppend()
    {
        csv += CSV_SEPARATOR;
    }
}
