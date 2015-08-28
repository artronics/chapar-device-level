package artronics.chapar.node;

import artronics.chapar.address.AbstractBaseAddress;
import artronics.chapar.app.logger.Log;

import java.util.ArrayList;

public abstract class AbstractBaseNode<A extends AbstractBaseAddress> implements Node
{
    protected final ArrayList<Link> links = new ArrayList<>();
    private final A address;
    private final NodeType type;

    protected AbstractBaseNode(A address)
    {
        this.type = NodeType.NORMAL;
        this.address = address;
    }

    protected AbstractBaseNode(A address, NodeType type)
    {
        this.type = type;
        this.address = address;
    }


    @Override
    public boolean hasLinkTo(Node node)
    {
        for (Link link : links)
            if (link.hasNode(node))
                return true;
        return false;
    }

    @Override
    public void addLinkTo(Node node, Quality quality)
    {
        Link link = NodeLinkFactory.create(this, node, quality);
        if (hasLinkTo(node)) {
            Log.main().debug("Try to make a link which already exists.");
            return;
        }
        links.add(link);
        node.getLinks().add(link);
    }

    @Override
    public A getAddress()
    {
        return address;
    }


    @Override
    public NodeType getType()
    {
        return this.type;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Node) {
            return address.equals(((Node) obj).getAddress());
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return ((Integer) address.intValue()).hashCode();
    }

    @Override
    public ArrayList<Link> getLinks()
    {
        return links;
    }
}
