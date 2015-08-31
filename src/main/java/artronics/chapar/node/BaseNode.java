package artronics.chapar.node;

import java.util.HashSet;
import java.util.Set;

final class BaseNode implements Node
{
    private final int address;
    private final HashSet<Link> links = new HashSet<>();
    private final NodeType type;

    public BaseNode(int address, NodeType type)
    {
        this.address = address;
        this.type = type;
    }

    public BaseNode(int address)
    {
        this.address = address;
        this.type = NodeType.NORMAL;
    }

    @Override
    public void addLinkTo(Node node, Quality quality)
    {
        if (hasLinkTo(node))
            return;

        Link link = new NodeLink(this, node, quality);
        links.add(link);
        node.getLinks().add(link);
    }

    @Override
    public Set<Link> getLinks()
    {
        return links;
    }

    @Override
    public boolean hasLinkTo(Node node)
    {
        for (Link link : links) {
            if (link.hasNode(node))
                return true;
        }

        return false;
    }

    @Override
    public int getAddress()
    {
        return address;
    }

    @Override
    public NodeType getType()
    {
        return type;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Node) {
            return ((Node) obj).getAddress()==this.address;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return ((Integer)address).hashCode();
    }

}
