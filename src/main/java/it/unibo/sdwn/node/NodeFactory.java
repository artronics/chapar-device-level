package it.unibo.sdwn.node;

public interface NodeFactory<N extends Node, A extends Address>
{
    N createNode(A address);

    N createSink(A address);
}
