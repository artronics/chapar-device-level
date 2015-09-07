package artronics.chapar.node;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.ClassBasedEdgeFactory;

public class LinkFactory extends ClassBasedEdgeFactory<Node, Link>
{
    public LinkFactory(
            Class<? extends Link> edgeClass)
    {
        super(edgeClass);
    }

    @Override
    public Link createEdge(Node source, Node target)
    {
        return new BaseLink();
    }
}

