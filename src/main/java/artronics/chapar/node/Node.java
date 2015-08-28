package artronics.chapar.node;

import artronics.chapar.address.AbstractBaseAddress;

import java.util.ArrayList;

interface Node<A extends AbstractBaseAddress>
{
    void addLinkTo(Node node, Quality quality);

    ArrayList<Link> getLinks();

    boolean hasLinkTo(Node node);

    A getAddress();

    NodeType getType();
}
