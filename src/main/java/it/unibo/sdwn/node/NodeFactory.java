package it.unibo.sdwn.node;

public interface NodeFactory<N extends AbstractBaseNode, A extends AbstractBaseAddress>
{
    N createNode(A address);

    N createSink(A address);
}
