package it.unibo.sdwn.node;

public interface NodeFactory<N, A>
{
    N createNode(A address, Node.Type type);

    N createSink(A address);

    N createNormalNode(A address);
}
