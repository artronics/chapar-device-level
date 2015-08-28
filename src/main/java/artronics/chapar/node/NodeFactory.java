package artronics.chapar.node;

import artronics.chapar.address.AbstractBaseAddress;

public interface NodeFactory<N extends AbstractBaseNode, A extends AbstractBaseAddress>
{
    N createNode(A address);

    N createSink(A address);
}
