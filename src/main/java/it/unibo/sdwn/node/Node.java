package it.unibo.sdwn.node;

import java.util.List;

public interface Node
{
    public List<Link> getLinks();

    public void addLinkTo(Node node);

    public Link getLinkTo(Node node);
    public Address getAddress();

    public void setAddress(Address address);

    boolean hasLinkTo(Node node);

}
