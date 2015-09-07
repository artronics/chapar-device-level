package artronics.chapar.map;

import artronics.chapar.node.BaseLink;
import artronics.chapar.node.BaseNode;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class BaseNetworkMap extends SimpleWeightedGraph<Node, Link>
        implements NetworkMap<Node, Link>
{
    private static final BaseNetworkMap INSTANCE = new BaseNetworkMap(BaseLink.class);

    private BaseNetworkMap(
            Class<? extends Link> edgeClass)
    {
        super(edgeClass);
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static BaseNetworkMap getInstance()
    {
        return INSTANCE;
    }

    @Override
    public void addNode(Node node)
    {
        this.addVertex(node);
    }

    @Override
    public void addLink(Node source, Node target, double weight)
    {
        Link link = this.addEdge(source, target);
        this.setEdgeWeight(link,weight);
    }

    @Override
    public boolean hasLink(Node source, Node target)
    {
        return this.containsEdge(source,target);
    }
}
