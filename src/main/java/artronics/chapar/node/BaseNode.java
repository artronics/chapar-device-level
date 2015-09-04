package artronics.chapar.node;

import java.util.HashSet;
import java.util.Set;

public class BaseNode extends AbstractNode<Integer, NodeType, Link>
{
    public BaseNode(int address, NodeType type)
    {
        super(address, type);
    }

    public BaseNode(int address)
    {
        super(address, NodeType.NORMAL);
    }

    @Override
    public void addLinkTo(Node node, int weight)
    {
        if (hasLinkTo(node))
            return;

        Link link = new SimpleLink(this, node, weight);
        links.add(link);
        node.getLinks().add(link);
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
        return address.hashCode();
    }

}
