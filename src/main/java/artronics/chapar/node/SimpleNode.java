package artronics.chapar.node;

import java.util.HashSet;
import java.util.Set;

public class SimpleNode extends AbstractNode<Integer, NodeType, Link>
{
    public SimpleNode(int address, NodeType type)
    {
        super(address, type);
    }

    public SimpleNode(int address)
    {
        super(address, NodeType.NORMAL);
    }

    @Override
    public void addLinkTo(Node node, int weigth)
    {
        if (hasLinkTo(node))
            return;

        Link link = new SimpleLink(this, node, weigth);
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
