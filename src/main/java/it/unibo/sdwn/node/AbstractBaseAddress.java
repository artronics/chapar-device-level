package it.unibo.sdwn.node;

public abstract class AbstractBaseAddress implements Address
{
    private final int addressValue;

    public AbstractBaseAddress(int addressValue)
    {
        this.addressValue = addressValue;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Address) {
            return addressValue == ((Address) obj).getAddress();
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Integer.hashCode(addressValue);
    }

    @Override
    public int getAddress()
    {
        return addressValue;
    }
}
