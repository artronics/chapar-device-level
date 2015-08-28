package artronics.chapar.packet;

import artronics.chapar.helper.UnsignedByte;

import java.util.ArrayList;

public class PacketToCsv
{
    //TODO change this class to non static.
    //TODO each line must end with two csv deparators
    //European countries use ";" as
    //CSV separator because "," is their digit separator
    public static final String CSV_SEPARATOR = "; ";
    private static String csv;


    public static String toCsv(AbstractBasePacket packet)
    {
        csv = "";
        csv += packet.getType();
        csvAppend();
        csv += packet.getDirection();
        csvAppend();
        csv += packet.getPacketSerialNumber();
        csvAppend();
        addPacketBytes(packet.getReceivedBytes());
        return csv;
    }

    private static void addPacketBytes(ArrayList<UnsignedByte> receivedBytes)
    {
        for (UnsignedByte ub : receivedBytes) {
            csv += ub.toString();
            csvAppend();
        }
    }

    private static void csvAppend()
    {
        csv += CSV_SEPARATOR;
    }
}
