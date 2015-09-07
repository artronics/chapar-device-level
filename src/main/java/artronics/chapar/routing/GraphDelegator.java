package artronics.chapar.routing;

import artronics.chapar.node.Node;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;

import java.util.List;

public class GraphDelegator
{
    private final Graph graph;

    public GraphDelegator(Graph graph)
    {
        this.graph = graph;
    }

    public List getShortestPath(Node source,Node target){
        DijkstraShortestPath dijkstra = new DijkstraShortestPath(graph,source,target);

        return dijkstra.getPathEdgeList();
    }


}
