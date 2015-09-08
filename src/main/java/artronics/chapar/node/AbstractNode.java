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
}
