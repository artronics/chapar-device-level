package artronics.chapar.routing;

import artronics.chapar.node.BaseNode;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import artronics.chapar.node.NodeLink;
import org.jgrapht.alg.DijkstraShortestPath;

import java.util.List;

public class NetworkSimpleGraph
{
    private final BaseSimpleGraph graph = new BaseSimpleGraph(NodeLink.class);

    public List<Link> getShortestPath(Node source, Node destination){
        DijkstraShortestPath<Node,Link> dijkstra = new DijkstraShortestPath
                <Node, Link>(graph,source,destination);
        return dijkstra.getPathEdgeList();
    }


}
