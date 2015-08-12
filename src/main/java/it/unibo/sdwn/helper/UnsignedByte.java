package it.unibo.sdwn.helper;

import it.unibo.sdwn.app.logger.Log;

import java.io.Serializable;

public class UnsignedByte extends Number implements Comparable<UnsignedByte>, Serializable
{
    private final byte value;

    public UnsignedByte()
    {
        this.value = 0;
    }

    public UnsignedByte(short value)
    {
        this.value = checkBounds(value);
    }

    public UnsignedByte(int value)
    {
        this.value = checkBounds(value);
    }

    public UnsignedByte(byte value)
    {
        this.value = value;
    }

    public static byte[] toByteArray(UnsignedByte[] array)
    {
        byte[] b = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            b[i] = array[i].byteValue();
        }
        return b;
    }

    private static byte checkBounds(int value)
    {
        if (value < 0 || value > 255) {
            Log.main().error("Tried to construct a byte with out-of-band value");
            throw new IllegalArgumentException("Value must be between 0 and 255");
        }
        return (byte) (value);
    }

    public static UnsignedByte[] toUnsignedByteArray(byte[] array) {
        UnsignedByte[] b = new UnsignedByte[array.length];
        for (int i = 0; i < array.length; i++) {
            b[i] = new UnsignedByte(array[i]);
        }
        return b;
    }

    @Override
    public int compareTo(UnsignedByte other)
    {
        return new Integer(this.intValue()).compareTo(new Integer(other.intValue()));
    }

    @Override
    public int hashCode()
    {
        return new Integer(value).hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof UnsignedByte && ((UnsignedByte) obj).value == value;
    }

    @Override
    public int intValue()
    {
        return value & 0xFF;// (value>=0) ? value : (256+value);
    }

    @Override
    public long longValue()
    {
        return value & 0xFF;// (value>=0) ? value : (256+value);
    }

    @Override
    public float floatValue()
    {
        return value & 0xFF;// (value>=0) ? value : (256+value);
    }

    @Override
    public short shortValue()
    {
        return (short) (value & 0xFF);//(value>=0) ? (short)value : (short)(value+256);
    }

    @Override
    public byte byteValue()
    {
        return this.value;
    }

    @Override
    public double doubleValue()
    {
        return value & 0xFF;// (value>=0) ? value : (256+value);
    }

    @Override
    public String toString()
    {
        return Integer.toString(this.intValue());
    }

}
