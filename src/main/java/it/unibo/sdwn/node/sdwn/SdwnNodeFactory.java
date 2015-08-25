package it.unibo.sdwn.node.sdwn;

import it.unibo.sdwn.node.Node;
import it.unibo.sdwn.node.NodeFactory;

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
