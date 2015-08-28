package artronics.chapar.address;

public interface AddressFactory<A extends AbstractBaseAddress>
{
    A create(int intValue);
}
