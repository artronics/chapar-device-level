package artronics.chapar.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

interface Node
{
    void addLinkTo(Node node, Quality quality);

    Set<Link> getLinks();

    boolean hasLinkTo(Node node);

    int getAddress();

    NodeType getType();
}
enum NodeType{
    SINK,
    NORMAL,
    ROUTER,
    END_NODE

}
