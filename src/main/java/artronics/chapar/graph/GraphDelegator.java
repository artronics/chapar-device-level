package artronics.chapar.graph;

import artronics.chapar.node.Node;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;

import java.util.*;

/**
 * The type Graph delegator.
 *
 * @param <N> the type parameter
 * @param <L> the type parameter
 */
public class GraphDelegator<N, L>
{
    private final Graph<N, L> graph;

    public GraphDelegator(Graph<N, L> graph)
    {
        this.graph = graph;
    }

    public List<N> getShortestPath(Node source, Node target){
        DijkstraShortestPath dijkstra = new DijkstraShortestPath(graph,source,target);
        List<L> links = dijkstra.getPathEdgeList();

        /*
            Dijkstra returns a list of all links but
            we need a list of nodes. A LinkedHashSet
            preserve the ordering of element and also
            ignore duplicated nodes. At the end we remove
            the source from set.
        */
        Set<N> nodes = new LinkedHashSet<>();
        for (L link : links) {
            nodes.add(graph.getEdgeSource(link));
            nodes.add(graph.getEdgeTarget(link));
        }
        nodes.remove(source);

        return new ArrayList<>(nodes);
    }
}
