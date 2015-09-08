package artronics.chapar.graph;

import artronics.chapar.map.NetworkMap;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;

import java.util.List;

public class GraphHelper<N extends Node, L extends Link>
{
    private final GraphDelegator<N, L> graphDelegator;

    public GraphHelper(NetworkMap<N, L> networkMap)
    {
        this.graphDelegator = new GraphDelegator(networkMap.getNetworkGraph());
    }

    /**
     * Get shortest path. This method returns a <code>List</code> containing all
     * <code>Node</code>s through target, containing {@code target} itself.
     * Remember this list does not contain the {@code source} node.
     *
     * @param source the source
     * @param target the target
     * @return the list
     */
    List<N> getShortestPath(Node source, Node target)
    {
        return graphDelegator.getShortestPath(source, target);
    }
}
