package artronics.chapar.graph;

import artronics.chapar.map.NetworkMap;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;

import java.util.List;

public class GraphHelper
{
    private final GraphDelegator<Node, Link> graphDelegator;

    public GraphHelper(NetworkMap<Node, Link> networkMap)
    {
        this.graphDelegator = new GraphDelegator(networkMap.getNetworkGraph());
    }

    /**
     * Get shortest path. This method returns a <code>List</code> containing all
     * <code>Node</code>s through target, containing both
     * {@code target} and {@code source}.
     *
     * @param source the source
     * @param target the target
     * @return the list
     */
    public List<Node> getShortestPath(Node source, Node target)
    {
        return graphDelegator.getShortestPath(source, target);
    }
}
