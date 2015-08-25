package it.unibo.sdwn.node;

public abstract class AbstractBaseAddress implements Address
{
    private final int addressValue;

    public AbstractBaseAddress(int addressValue)
    {
        this.addressValue = addressValue;
    }

    @Override
    public int getAddress()
    {
        return addressValue;
    }
}
