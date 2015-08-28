package artronics.chapar.node.sdwn;

import artronics.chapar.address.sdwn.SdwnAddress;
import artronics.chapar.node.NodeFactory;
import artronics.chapar.node.NodeType;

public final class SdwnNodeFactory implements NodeFactory<SdwnNode, SdwnAddress>
{
    @Override
    public SdwnNode createNode(SdwnAddress address)
    {
        return new SdwnNode(address, NodeType.NORMAL);
    }

    @Override
    public SdwnNode createSink(SdwnAddress address)
    {
        return new SdwnNode(address, NodeType.SINK);
    }
}
