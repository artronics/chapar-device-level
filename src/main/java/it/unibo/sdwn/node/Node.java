package it.unibo.sdwn.node;

import it.unibo.sdwn.app.logger.Log;

import java.util.ArrayList;
import java.util.List;

public class Node implements INode
{
    private Address address;
    private List<Link> links = new ArrayList<>();

    public Node(Address nodeAddr)
    {
        this.address = nodeAddr;
    }

    public Node(int intAddr)
    {
        Address addr = new Address(intAddr);
        this.address = addr;
    }

    @Override
    public boolean hasLinkTo(INode node)
    {
        for (int i = 0; i < this.links.size(); ++i) {
            if (this.links.get(i).getDestinationNode() == node)
                return true;
        }
        return false;
    }

    @Override
    public Link getLinkTo(INode node)
    {
        if (hasLinkTo(node)) {
            for (Link link : this.links) {
                if (link.getDestinationNode() == node)
                    return link;
            }
        }
        Log.main().error("Try to get non existence link");
        return null;
    }


    @Override
    public final void addLinkTo(INode node, Quality linkQuality)
    {
        if (hasLinkTo(node)) {
            Log.main().error("Tried to create a duplicated link");
        }
        else{
            Link link = new CommunicationLink(node, linkQuality);
            this.links.add(link);
            //add link in opposite direction in case of
            //HALF_DUPLEX OR FULL_DUPLEX
            if ((link.getLinkType() == Link.LinkType.FULL_DUPLEX ||
                    link.getLinkType() == Link.LinkType.HALF_DUPLEX) &&
                    !node.hasLinkTo(this))
            {
                if (LinkQuality.RICIPROCAL_QUALITY)
                    node.addLinkTo(this, linkQuality);
                else {
                    node.addLinkTo(this, null);
                    Log.main().warn("Try to add a link with null value for Quality");
                }
            }
        }
    }

    @Override
    public final List<Link> getLinks()
    {
        return this.links;
    }

    @Override
    public final Address getAddress()
    {
        return this.address;
    }

    @Override
    public final void setAddress(Address address)
    {
        this.address = address;
    }
}
