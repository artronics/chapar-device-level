package it.unibo.sdwn.node;

public class BaseNode extends AbstractBaseNode
{
    protected BaseNode(BaseAddress address, Type type)
    {
        super(address, type);
    }

    protected BaseNode(BaseAddress address)
    {
        super(address);
    }

    @Override
    public boolean hasLinkTo(Node node)
    {
//        return links.contains()
        return false;
    }
}
