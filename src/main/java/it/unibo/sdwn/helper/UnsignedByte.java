package it.unibo.sdwn.helper;

import it.unibo.sdwn.app.logger.Log;

import java.io.Serializable;

public class UnsignedByte extends Number implements Comparable<UnsignedByte>, Serializable
{
    private final int value;

    private UnsignedByte(Number number)
    {
        this.value = checkBounds(number);
    }

    private int checkBounds(Number number)
    {
        int intValue = number.intValue();
        //deals with negative byte value
        if (intValue < 0 && intValue >= -128) {
            intValue += 256;
        }
        if (intValue < 0 || intValue > 255) {
            Log.main().error("Tried to construct a byte with out-of-band value");
            throw new IllegalArgumentException("Value must be between 0 and 255");
        }

        return intValue;
    }

    public static byte[] toByteArray(UnsignedByte[] array)
    {
        byte[] b = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            b[i] = array[i].byteValue();
        }
        return b;
    }

    public static UnsignedByte[] toUnsignedByteArray(byte[] array)
    {
        UnsignedByte[] b = new UnsignedByte[array.length];
        for (int i = 0; i < array.length; i++) {
            b[i] = UnsignedByte.of(array[i]);
        }
        return b;
    }

    public static UnsignedByte of(Number number)
    {
        UnsignedByte unsignedByte = new UnsignedByte(number);
        return unsignedByte;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof UnsignedByte) {
            return value == ((UnsignedByte) obj).intValue();
        }
        return false;
    }

    @Override
    public String toString()
    {
        return Integer.toString(this.value);
    }

    @Override
    public int intValue()
    {
        return this.value;
    }

    @Override
    public long longValue()
    {
        return (long) this.value;
    }

    @Override
    public float floatValue()
    {
        return (float) this.value;
    }

    @Override
    public double doubleValue()
    {
        return (double) this.value;
    }

    @Override
    public int compareTo(UnsignedByte anotherUnsignedByte)
    {
        return compare(this.value, anotherUnsignedByte.value);
    }

    public static int compare(int x, int y)
    {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

}
