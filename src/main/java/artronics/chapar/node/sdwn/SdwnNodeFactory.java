package artronics.chapar.node.sdwn;

import artronics.chapar.node.Node;
import artronics.chapar.node.NodeFactory;

public class SdwnNodeFactory implements NodeFactory<SdwnNode, SdwnAddress>
{
    @Override
    public SdwnNode createNode(SdwnAddress address)
    {
        return new SdwnNode(address, Node.Type.NORMAL);
    }

    @Override
    public SdwnNode createSink(SdwnAddress address)
    {
        return new SdwnNode(address, Node.Type.SINK);
    }
}
