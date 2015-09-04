package artronics.chapar.node;

import java.util.Set;

public interface Node<A, T, L>
{
    void addLinkTo(Node node, int wieght);

    Set<L> getLinks();

    boolean hasLinkTo(Node node);

    A getAddress();

    T getType();

}
