package it.unibo.sdwn.Packet;

public class PacketSerializer
{
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
        csv += AbstractBasePacket.getPacketSerialNumber();
        csvAppend();
        return csv;
    }

    private static void csvAppend()
    {
        csv += CSV_SEPARATOR;
    }
}
