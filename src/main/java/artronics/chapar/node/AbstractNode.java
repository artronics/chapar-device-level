package artronics.chapar.node;

public class AbstractNode<A> implements Node<A>
{
    private final A address;

    public AbstractNode(A address)
    {
        this.address = address;
    }

    @Override
    public A getAddress()
    {
        return address;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Node) {
            Node node = (Node) obj;
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
