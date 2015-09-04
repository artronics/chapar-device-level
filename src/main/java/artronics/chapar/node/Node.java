package artronics.chapar.node;

import java.util.List;
import java.util.Set;

public interface Node<A, T, L>
{
    void addLinkTo(Node node, int wieght);

    List<L> getLinks();

    boolean hasLinkTo(Node node);

    A getAddress();

    T getType();

}
