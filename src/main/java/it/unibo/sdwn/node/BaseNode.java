package it.unibo.sdwn.node;

public abstract class BaseNode implements Node
{
    private Address address;

    public BaseNode(Address nodeAddr)
    {
        this.address = nodeAddr;
    }

    @Override
    public Address getAddress()
    {
        return this.address;
    }

    @Override
    public void setAddress(Address address)
    {
        this.address = address;
    }
}
