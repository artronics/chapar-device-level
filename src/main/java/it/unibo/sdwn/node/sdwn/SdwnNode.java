package it.unibo.sdwn.node.sdwn;

import it.unibo.sdwn.node.AbstractBaseAddress;
import it.unibo.sdwn.node.AbstractBaseNode;
import it.unibo.sdwn.node.Node;

public final class SdwnNode extends AbstractBaseNode
{
    public SdwnNode(AbstractBaseAddress address)
    {
        super(address);
    }

    protected SdwnNode(SdwnAddress address, Node.Type type)
    {
        super(address, type);
    }

    @Override
    public void addLinkTo(Node node)
    {

    }
}
