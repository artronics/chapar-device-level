package artronics.chapar.map;

import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import org.jgrapht.Graph;

public interface NetworkMap<N extends Node, L extends Link>
{
    void addNode(N node);

    void addLink(N source, N target, double weight);

    boolean hasLink(N source, N target);

    Graph<N, L> getNetworkGraph();

    boolean contains(N node);
}
