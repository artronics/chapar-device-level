package it.unibo.sdwn.node.sdwn;

import it.unibo.sdwn.node.AbstractBaseNode;
import it.unibo.sdwn.node.Node;

public final class SdwnNode extends AbstractBaseNode
{
    protected SdwnNode(SdwnAddress address)
    {
        super(address);
    }


    protected SdwnNode(SdwnAddress address, Node.Type type)
    {
        super(address, type);
    }

}
