package artronics.chapar.map;

import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import org.jgrapht.Graph;

import java.util.List;

public interface NetworkMap<N extends Node, L extends Link>
{
    void addNode(N node);

    void addLink(N source, N target, double weight);

    boolean hasLink(N source, N target);

    boolean contains(N node);

    List<N> getAllNodes();

    Graph<N, L> getNetworkGraph();

}
