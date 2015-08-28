package artronics.chapar.node.sdwn;

import artronics.chapar.address.sdwn.SdwnAddress;
import artronics.chapar.node.AbstractBaseNode;
import artronics.chapar.node.NodeType;

public class SdwnNode extends AbstractBaseNode<SdwnAddress>
{
    protected SdwnNode(SdwnAddress address)
    {
        super(address);
    }


    protected SdwnNode(SdwnAddress address, NodeType type)
    {
        super(address, type);
    }
}
