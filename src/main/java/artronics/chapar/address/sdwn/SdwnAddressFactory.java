package artronics.chapar.address.sdwn;

import artronics.chapar.address.AddressFactory;

public final class SdwnAddressFactory implements AddressFactory<SdwnAddress>
{
    @Override
    public SdwnAddress create(int intValue)
    {
        return new SdwnAddress(intValue);
    }
}
