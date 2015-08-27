package it.unibo.sdwn.node;

import it.unibo.sdwn.app.logger.Log;

import java.util.ArrayList;

public abstract class AbstractBaseNode<A extends AbstractBaseAddress> implements Node
{
    private final A address;
    private final Node.Type type;
    protected ArrayList<Link> links = new ArrayList<>();

    protected AbstractBaseNode(A address)
    {
        this.type = Type.NORMAL;
        this.address = address;
    }

    protected AbstractBaseNode(A address, Node.Type type)
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
    public AbstractBaseAddress getAddress()
    {
        return address;
    }


    @Override
    public Type getType()
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
