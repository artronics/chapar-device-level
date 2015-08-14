package it.unibo.sdwn.node;

import it.unibo.sdwn.helper.UnsignedByte;

public interface Quality
{
    public static final boolean RICIPROCAL_QUALITY=true;
    public UnsignedByte getValue();

    public void setValue(UnsignedByte value);
}
