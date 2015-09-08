package artronics.chapar.node;

public class SimpleNode extends AbstractNode<Integer>
{
    public SimpleNode(Integer address)
    {
        super(address);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof SimpleNode) {
            SimpleNode node = (SimpleNode) obj;
            return getAddress().equals(node.getAddress());
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        return getAddress().hashCode();
    }
}
