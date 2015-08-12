package it.unibo.sdwn.node;

public class Node implements INode
{
    private Address address;

    public Node(Address nodeAddr)
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
