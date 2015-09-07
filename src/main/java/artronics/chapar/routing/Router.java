package artronics.chapar.routing;

import artronics.chapar.map.BaseNetworkMap;
import artronics.chapar.map.NetworkMap;
import artronics.chapar.map.SimpleNetworkMap;
import artronics.chapar.node.Node;
import artronics.chapar.node.BaseLink;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;

import java.util.List;

public class Router
{
    private final NetworkMap networkMap;
    private final GraphAdapter graphAdapter;

    public Router(GraphAdapter graphAdapter, NetworkMap networkMap)
    {
        this.networkMap = networkMap;
        this.graphAdapter = graphAdapter;
    }

    List getShortestPath(Node source, Node target){

        return graphAdapter.getShortestPath(source,target);
    }
}
