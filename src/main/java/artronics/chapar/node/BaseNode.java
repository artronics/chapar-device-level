package artronics.chapar.node;

import artronics.chapar.address.BaseAddress;

public class BaseNode extends AbstractBaseNode
{
    protected BaseNode(BaseAddress address, NodeType type)
    {
        super(address, type);
    }

    protected BaseNode(BaseAddress address)
    {
        super(address);
    }
}
