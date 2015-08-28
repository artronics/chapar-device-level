package artronics.chapar.node.sdwn;

import artronics.chapar.address.sdwn.SdwnAddress;
import artronics.chapar.node.AbstractBaseNode;
import artronics.chapar.node.Node;

public final class SdwnNode extends AbstractBaseNode<SdwnAddress>
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
