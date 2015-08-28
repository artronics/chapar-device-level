package artronics.chapar.node;

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
}
