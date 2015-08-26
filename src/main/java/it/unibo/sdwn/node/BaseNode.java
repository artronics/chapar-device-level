package it.unibo.sdwn.node;

public class BaseNode extends AbstractBaseNode
{
    public BaseNode(BaseAddress address, Type type)
    {
        super(address, type);
    }

    protected BaseNode(BaseAddress address)
    {
        super(address);
    }

    @Override
    public void addLinkTo(Node node)
    {

    }
}
