package it.unibo.sdwn.node;

import it.unibo.sdwn.helper.UnsignedByte;

import java.io.Serializable;

public class Address implements Comparable<Address>, Serializable
{
    protected final UnsignedByte[] address=new UnsignedByte[2];

    public Address()
    {
    }

    public Address(int addrInt)
    {
        splitBytes(addrInt);
    }

    public UnsignedByte[] getAddress()
    {
        return checkNull();
    }
    public UnsignedByte[] getAddress(int addrInt)
    {
        splitBytes(addrInt);
        return checkNull();
    }

    private void splitBytes(int addrInt)
    {
        UnsignedByte firstByte = new UnsignedByte(addrInt>>8);
        UnsignedByte secondByte = new UnsignedByte(addrInt & 0xFF);
        this.address[0] = firstByte;
        this.address[1] = secondByte;
    }

    private UnsignedByte[] checkNull()
    {
        if (this.address != null) {
            return this.address;
        }else throw new NullPointerException("Address is null");
    }


    public int intValue() {
        return this.address[0].intValue() * 256 + this.address[1].intValue();
    }

    @Override
    public String toString() {
        return this.address[0].toString() + "." + this.address[1].toString();
    }

    @Override
    public int compareTo(Address other) {
        return new Integer(this.intValue()).compareTo(new Integer(other.intValue()));
    }

    @Override
    public int hashCode() {
        return new Integer(this.intValue()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Address && ((Address) obj).intValue() == this.intValue();
    }
}
