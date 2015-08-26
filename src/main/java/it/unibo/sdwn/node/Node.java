package it.unibo.sdwn.node;

public interface Node
{
    boolean hasLinkTo(Node node);

    Address getAddress();
    Type getType();

    enum Type
    {
        SINK,
        NORMAL,
        ROUTER,
        END_NODE
    }
}
