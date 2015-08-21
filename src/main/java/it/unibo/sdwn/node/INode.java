package it.unibo.sdwn.node;

import java.util.List;

public interface INode
{
    public List<Link> getLinks();

    public void addLinkTo(INode node, Quality linkQuality);
    public Link getLinkTo(INode node);
    public Address getAddress();

    public void setAddress(Address address);

    boolean hasLinkTo(INode node);

}
