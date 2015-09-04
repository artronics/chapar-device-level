package artronics.chapar.node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract class AbstractNode<A, T, L> implements Node<A, T, L>
{
    protected final A address;
    protected final List<L> links = new ArrayList<>();
    protected final T type;

    protected AbstractNode(A address, T type)
    {
        this.address = address;
        this.type = type;
    }

    @Override
    public T getType()
    {
        return type;
    }

    public abstract void addLinkTo(Node node, int wiegth);

    @Override
    public List<L> getLinks()
    {
        return links;
    }

    public abstract boolean hasLinkTo(Node node);

    public abstract boolean equals(Object otherNode);

    public abstract int hashCode();

    @Override
    public A getAddress()
    {
        return address;
    }
}
