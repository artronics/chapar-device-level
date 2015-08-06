package it.unibo.sdwn.Packet;

public abstract class Packet {

    //Common properties for all packets
    protected int length;

    public abstract int getLength();
    public abstract byte[] getPacketBytes();

}
