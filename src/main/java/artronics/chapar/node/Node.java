package artronics.chapar.node;

import java.util.ArrayList;
import java.util.List;

interface Node
{
    void addLinkTo(Node node, Quality quality);

    List<? super Link> getLinks();

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
