package artronics.chapar.map;

import org.jgrapht.Graph;

public interface NetworkMap<N, L>
{
    void addNode(N node);

    void addLink(N source, N target, double weight);

    boolean hasLink(N source, N target);

    Graph<N, L> getNetworkGraph();
}
