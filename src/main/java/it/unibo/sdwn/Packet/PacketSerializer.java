package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

public class PacketSerializer
{
    //European countries use ";" as
    //CSV separator because "," is their digit separator
    public static final String CSV_SEPARATOR = "; ";
    private String csv;
    private AbstractBasePacket packet;

    public PacketSerializer(AbstractBasePacket packet)
    {
        this.packet = packet;
    }

    public String toCsv()
    {
        csv = "";
        csv += packet.getType();
        csvAppend();
        csv += packet.getDirection();
        csvAppend();
        csv += packet.getPacketSerialNumber();
        csvAppend();
        addPacketBytes();
        return csv;
    }

    private void addPacketBytes()
    {
        ArrayList<UnsignedByte> receivedBytes = packet.getReceivedBytes();
        for (UnsignedByte ub : receivedBytes) {
            csv += ub.toHex();
            csvAppend();
        }
    }

    private void csvAppend()
    {
        csv += CSV_SEPARATOR;
    }
}
