package it.unibo.sdwn.node;

public interface Node
{

    enum Type
    {
        SINK,
        NORMAL,
        ROUTER,
        END_NODE
    }
}
