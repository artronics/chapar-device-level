package it.unibo.sdwn.node;

import it.unibo.sdwn.helper.UnsignedByte;

public class LinkQuality implements Quality
{
    private UnsignedByte value;

    public LinkQuality(int qualityValue)
    {
        this.value = new UnsignedByte(qualityValue);
    }

    public LinkQuality(UnsignedByte value)
    {
        this.value = value;
    }

    @Override
    public UnsignedByte getValue()
    {
        return this.value;
    }

    @Override
    public void setValue(UnsignedByte value)
    {
        this.value = value;
    }
}
