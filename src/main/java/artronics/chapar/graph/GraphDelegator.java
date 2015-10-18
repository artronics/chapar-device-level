package artronics.chapar.graph;

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

    public List<N> getShortestPath(N source, N target)
    {
        DijkstraShortestPath dijkstra = new DijkstraShortestPath(graph, source, target);
        List<L> links = dijkstra.getPathEdgeList();

        /*
            Dijkstra returns a list of all links but
            we need a list of nodes. A LinkedHashSet
            preserve the ordering of element and also
            ignore duplicated nodes. At the end we remove
            the source from set. This is because in case
            reversed direction from source to target
            we'll get a wrong order. We'll add source to
            final list.
        */
        Set<N> nodes = new LinkedHashSet<>();

        for (L link : links) {
            nodes.add(graph.getEdgeSource(link));
            nodes.add(graph.getEdgeTarget(link));
        }
        nodes.remove(source);

        List<N> nodesList = new ArrayList<>(nodes);
        nodesList.add(0, source);

        return nodesList;
    }
}
