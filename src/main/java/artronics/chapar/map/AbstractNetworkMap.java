package artronics.chapar.map;

import artronics.chapar.gui.GraphGui;
import artronics.chapar.node.BaseLink;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import org.jgrapht.Graph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public abstract class AbstractNetworkMap<N extends Node, L extends Link>
        implements NetworkMap<N, L>
{
    protected final ListenableUndirectedWeightedGraph<N, L> graph;

    public AbstractNetworkMap(Class<? extends L> linkClass)
    {
        this.graph = new ListenableUndirectedWeightedGraph<N, L>(linkClass);

//        GraphGui<N, L> graphGui = new GraphGui<>((ListenableUndirectedWeightedGraph) graph);
//        graphGui.start();
    }

    @Override
    public void addNode(N node)
    {
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
    public List<N> getAllNodes()
    {
        return new ArrayList<>(graph.vertexSet());
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
