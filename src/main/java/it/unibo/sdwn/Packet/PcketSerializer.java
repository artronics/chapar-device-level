package it.unibo.sdwn.Packet;

public class PcketSerializer
{
    //European countries use ";" as
    //CSV separator because "," is their digit separator
    public static final String CSV_SEPARATOR = "; ";
    private static String csv;

    public static String toCsv(SdwnPacket packet)
    {
        csv = "";
        csv += packet.getType();
        csvAppend();
        csv += SdwnPacket.getRecievedCounter();
        return csv;
    }

    private static void csvAppend()
    {
        csv += CSV_SEPARATOR;
    }
}
