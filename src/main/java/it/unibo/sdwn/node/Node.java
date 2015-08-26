package it.unibo.sdwn.node;

public interface Node
{
    void addLinkTo(Node node, Quality quality);

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
