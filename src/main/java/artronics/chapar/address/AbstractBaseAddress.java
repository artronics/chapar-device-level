package artronics.chapar.address;

public abstract class AbstractBaseAddress implements Address
{
    protected final int addressValue;

    public AbstractBaseAddress(int addressValue)
    {
        this.addressValue = addressValue;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Address) {
            return addressValue == ((Address) obj).intValue();
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Integer.hashCode(addressValue);
    }

    @Override
    public int intValue()
    {
        return addressValue;
    }
}
