package it.unibo.sdwn.node;

public interface Node
{
    void addLinkTo(Node node);

    enum Type
    {
        SINK,
        NORMAL,
        ROUTER,
        END_NODE
    }
}
