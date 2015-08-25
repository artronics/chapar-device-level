package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.analyser.Analysable;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

//Todo it must have a minimum length
public abstract class AbstractBasePacket implements Packet, Analysable
{
    private static long packetSerialNumber = 0;
    protected final Type packetType;
    protected final Direction direction;
    private final ArrayList receivedBytes;

    /**
     * This id the minimum requirement for constructing a packet. We need Packet.Type and the Direction of packet and
     * also received bytes. Then logger will log the constructed packet which might be even malformed packet.
     */
    protected AbstractBasePacket(Type packetType, Direction dir, ArrayList receivedBytes)
    {
        this.packetType = packetType;
        this.direction = dir;
        this.receivedBytes = new ArrayList(receivedBytes);
        packetSerialNumber++;
        this.logPacket();
    }



    public static long getPacketSerialNumber()
    {
        return packetSerialNumber;
    }

    private static synchronized void incReceivedCounter()
    {
        packetSerialNumber++;
    }

    public Direction getDirection()
    {
        return direction;
    }

    private void logPacket()
    {
        String s = this.toCsv();
        Log.packet().info(s);
        Log.main().info(s);
    }

    @Override
    public String toCsv()
    {
        return PacketSerializer.toCsv(this);
    }

    public ArrayList<UnsignedByte> getReceivedBytes()
    {
        return this.receivedBytes;
    }

    public Type getType()
    {
        return packetType;
    }

}
