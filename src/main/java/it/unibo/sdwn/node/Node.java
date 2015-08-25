package it.unibo.sdwn.node;

public interface Node
{
    void addLinkTo(Node node);

    Type getType();

    enum Type
    {
        SINK,
        NORMAL,
        ROUTER,
        END_NODE
    }
}
