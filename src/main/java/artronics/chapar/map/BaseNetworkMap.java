package artronics.chapar.map;

import artronics.chapar.node.BaseLink;
import artronics.chapar.node.BaseNode;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class BaseNetworkMap implements NetworkMap<Node, Link>
{
    private static final BaseNetworkMap INSTANCE = new BaseNetworkMap();

    private final SimpleWeightedGraph<Node, Link> graph = new
            SimpleWeightedGraph(
            BaseLink.class);

    private BaseNetworkMap()
    {
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
        this.graph.addVertex(node);
    }

    @Override
    public void addLink(Node source, Node target, double weight)
    {
        Link link = this.graph.addEdge(source, target);
        this.graph.setEdgeWeight(link, weight);
    }

    @Override
    public boolean hasLink(Node source, Node target)
    {
        return this.graph.containsEdge(source, target);
    }

    @Override
    public Graph<Node, Link> getNetworkGraph()
    {
        return this.graph;
    }
}
