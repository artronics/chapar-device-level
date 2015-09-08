package artronics.chapar.map;

import artronics.chapar.node.BaseLink;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.HashSet;
import java.util.Hashtable;

public abstract class AbstractNetworkMap<N extends Node, L extends Link>
        implements NetworkMap<N, L>
{
    protected final SimpleWeightedGraph<N, L> graph;

//    protected final HashSet<N> nodeSet = new HashSet<>();


    public AbstractNetworkMap(Class<? extends BaseLink> linkClass)
    {
        this.graph = new SimpleWeightedGraph(linkClass);
    }

    @Override
    public void addNode(N node)
    {
//        nodeSet.add(node);
        graph.addVertex(node);
    }

    @Override
    public abstract void addLink(N source, N target, double weight);

    @Override
    public boolean hasLink(N source, N target)
    {
        return graph.containsEdge(source, target);
    }

    @Override
    public Graph<N, L> getNetworkGraph()
    {
        return graph;
    }

    @Override
    public boolean contains(N node)
    {
//        return nodeSet.contains(node);
        return graph.containsVertex(node);
    }
}
