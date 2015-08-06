package it.unibo.sdwn.Packet;

public interface ByteStream {
    public byte getByte(int index);
    public void setByte(int index, byte value);
}
