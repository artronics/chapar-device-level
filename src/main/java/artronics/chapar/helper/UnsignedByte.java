package artronics.chapar.helper;

import artronics.chapar.app.logger.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class UnsignedByte extends Number implements Comparable<UnsignedByte>, Serializable
{
    private final int value;

    private UnsignedByte(Number number)
    {
        this.value = checkBounds(number);
    }

    public static byte[] toByteArray(UnsignedByte[] array)
    {
        byte[] b = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            b[i] = array[i].byteValue();
        }
        return b;
    }

    public static ArrayList<UnsignedByte> toUnsignedByteArrayList(byte[] buff, int length)
    {
        ArrayList<UnsignedByte> bytes = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            bytes.add(UnsignedByte.of(buff[i]));
        }
        return bytes;
    }

    public static UnsignedByte of(Number number)
    {
        UnsignedByte unsignedByte = new UnsignedByte(number);
        return unsignedByte;
    }

    public static int compare(int x, int y)
    {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    private int checkBounds(Number number)
    {
        int intValue = number.intValue();
        if (intValue < -128 || intValue > 255) {
            Log.main().error("Tried to construct a byte with out-of-band value");
            throw new IllegalArgumentException("Value must be between 0 and 255");
        }
        //deals with negative byte value
        intValue = intValue & 0xff;

        return intValue;
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

    public String toHex()
    {
        return String.format("0x%X", this.value);
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

}
