package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.analyser.Analysable;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

public abstract class AbstractBasePacket<PT extends PacketType>implements Analysable
{
    private static long packetSerialNumber = 0;
    protected final PT packetType;
    protected final Packet.Direction direction;
    private final ArrayList receivedBytes;
    private final String csv; //We use this for analyzing packet(write as csv)

    /**
     * This id the minimum requirement for constructing a packet. We need Packet.Type and the Direction of packet and
     * also received bytes. Then logger will log the constructed packet which might be even malformed packet.
     */
    protected AbstractBasePacket(PT packetType, Packet.Direction dir, ArrayList receivedBytes)
    {
        this.packetType = packetType;
        this.direction = dir;
        this.receivedBytes = new ArrayList(receivedBytes);
        packetSerialNumber++;

        this.csv = PacketSerializer.toCsv(this);
        this.writeCsv();
    }


    public static long getPacketSerialNumber()
    {
        return packetSerialNumber;
    }

    private static synchronized void incReceivedCounter()
    {
        packetSerialNumber++;
    }

    public Packet.Direction getDirection()
    {
        return direction;
    }

    private void writeCsv()
    {
        Log.packet().info(toCsv()); //write to file
        Log.main().info(toCsv());// write to console
    }

    @Override
    public String toCsv()
    {
        return this.csv;
    }

    public ArrayList<UnsignedByte> getReceivedBytes()
    {
        return this.receivedBytes;
    }

    public PacketType getType()
    {
        return packetType;
    }

}
