package artronics.chapar.node;

import org.jgrapht.EdgeFactory;

public class BaseLinkFactory implements EdgeFactory<Node,Link>
{
    @Override
    public Link createEdge(Node sourceVertex, Node targetVertex)
    {
        return null;
    }
}
