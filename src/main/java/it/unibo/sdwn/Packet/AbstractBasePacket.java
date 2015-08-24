package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.analyser.Analysable;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

//Todo it must have a minimum length
public abstract class AbstractBasePacket implements Packet, Analysable
{
    private static long packetSerialNumber = 0;
    private ArrayList<UnsignedByte> data;

    protected AbstractBasePacket()
    {
        packetSerialNumber++;
    }

    public static long getPacketSerialNumber()
    {
        return packetSerialNumber;
    }

    private static synchronized void incReceivedCounter()
    {
        packetSerialNumber++;
    }

    private void logPacket()
    {
        Log.packet().info(this.toCsv());
    }

    @Override
    public String toCsv()
    {
        return PacketSerializer.toCsv(this);
    }

    @Override
    public String toString()
    {
        return toCsv();
    }


    @Override
    public ArrayList<UnsignedByte> getBytes()
    {
        return null;
    }

    @Override
    public Type getType()
    {
        return null;
    }

    @Override
    public int getLength()
    {
        return 0;
    }
}
