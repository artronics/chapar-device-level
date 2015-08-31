package artronics.chapar.node;

import java.util.Set;

public interface Node
{
    void addLinkTo(Node node, Quality quality);

    Set<Link> getLinks();

    boolean hasLinkTo(Node node);

    int getAddress();

    NodeType getType();

    enum NodeType{
        SINK,
        NORMAL,
        ROUTER,
        END_NODE

    }
}
