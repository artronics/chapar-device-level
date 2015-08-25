package it.unibo.sdwn.node;

public abstract class AbstractBaseNode implements Node
{
    private final AbstractBaseAddress address;
    private final Node.Type type;

    protected AbstractBaseNode(AbstractBaseAddress address)
    {
        this.type = Type.NORMAL;
        this.address = address;
    }

    protected AbstractBaseNode(AbstractBaseAddress address, Node.Type type)
    {
        this.type = type;
        this.address = address;
    }
}
