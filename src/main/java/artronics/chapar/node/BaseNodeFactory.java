package artronics.chapar.node;


import artronics.chapar.address.BaseAddress;

/**
 * The type Base node factory.
 * The reason for this class is to create BaseNodes in test classes. Do not extend this
 * class unless you need a general purpose BaseNode. For example if you want to
 * test some graph algorithms BaseNode is the best option.
 */
public class BaseNodeFactory implements NodeFactory<BaseNode, BaseAddress>
{

    @Override
    public BaseNode createNode(BaseAddress address)
    {
        return new BaseNode(address);
    }

    @Override
    public BaseNode createSink(BaseAddress address)
    {
        return new BaseNode(address, NodeType.SINK);
    }
}
