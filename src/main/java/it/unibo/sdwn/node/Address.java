package it.unibo.sdwn.node;

import it.unibo.sdwn.helper.UnsignedByte;

import java.io.Serializable;

public class Address implements Comparable<Address>, Serializable
{
    protected final UnsignedByte[] address=new UnsignedByte[2];

    public Address()
    {
    }

    public Address(UnsignedByte addrUb)
    {
        this.address[0] = addrUb;
        this.address[1] = UnsignedByte.of(0);
    }

    public Address(int addrInt)
    {
        splitBytes(addrInt);
    }

    private void splitBytes(int addrInt)
    {
        UnsignedByte firstByte = UnsignedByte.of(addrInt >> 8);
        UnsignedByte secondByte = UnsignedByte.of(addrInt & 0xFF);
        this.address[0] = firstByte;
        this.address[1] = secondByte;
    }

    public UnsignedByte[] getAddress()
    {
        return checkNull();
    }

    private UnsignedByte[] checkNull()
    {
        if (this.address != null) {
            return this.address;
        }else throw new NullPointerException("Address is null");
    }

    public UnsignedByte[] getAddress(int addrInt)
    {
        splitBytes(addrInt);
        return checkNull();
    }

    @Override
    public int compareTo(Address other)
    {
        return new Integer(this.intValue()).compareTo(new Integer(other.intValue()));
    }

    public int intValue() {
        return this.address[0].intValue() * 256 + this.address[1].intValue();
    }

    @Override
    public int hashCode()
    {
        return new Integer(this.intValue()).hashCode();
    }

}
