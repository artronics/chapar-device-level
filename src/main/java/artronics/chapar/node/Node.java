package artronics.chapar.node;

import artronics.chapar.address.Address;

import java.util.ArrayList;

public interface Node
{
    void addLinkTo(Node node, Quality quality);

    ArrayList<Link> getLinks();

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
