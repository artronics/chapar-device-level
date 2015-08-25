package it.unibo.sdwn.node;

public interface NodeFactory<N, A>
{
    N createNode(A address);

    N createSink(A address);
}
