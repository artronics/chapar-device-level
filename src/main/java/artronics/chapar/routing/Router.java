package artronics.chapar.routing;

import artronics.chapar.map.NetworkMap;
import artronics.chapar.node.Node;

import java.util.List;

public class Router
{
    private final GraphDelegator graphDelegator;

    public Router(NetworkMap networkMap)
    {
        this.graphDelegator = new GraphDelegator(networkMap.getNetworkGraph());
    }

    List getShortestPath(Node source, Node target){

        return graphDelegator.getShortestPath(source, target);
    }
}
